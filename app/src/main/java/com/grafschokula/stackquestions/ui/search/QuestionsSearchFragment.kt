package com.grafschokula.stackquestions.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.grafschokula.stackquestions.R
import com.grafschokula.stackquestions.data.model.Question
import com.grafschokula.stackquestions.data.repository.NetworkState
import com.grafschokula.stackquestions.data.repository.Status
import com.grafschokula.stackquestions.databinding.FragmentQuestionsSearchBinding
import com.grafschokula.stackquestions.di.module.ViewModelFactory
import com.grafschokula.stackquestions.di.qualifier.ActivityScoped
import com.grafschokula.stackquestions.ui.base.BaseFragment
import com.grafschokula.stackquestions.ui.detail.QuestionDetailActivity
import javax.inject.Inject

/**
 *Created by Tim on 19, July, 2019
 **/
@ActivityScoped
class QuestionsSearchFragment @Inject constructor() : BaseFragment<FragmentQuestionsSearchBinding>(),
    SelectedQuestionListener {

    companion object {
        const val QUESTION_LINK = "question_link"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: QuestionsSearchViewModel

    override fun getLayoutResId() = R.layout.fragment_questions_search


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuestionsSearchViewModel::class.java)
        subscribeUi()
    }

    private fun subscribeUi() {

        val title = viewModel.getTitle().value

        activity?.title = "#$title"

        binding.swipeRefresh.apply {
            viewModel.refreshState.observe(this@QuestionsSearchFragment, Observer {
                isRefreshing = it == NetworkState.LOADING
            })

            setOnRefreshListener {
                viewModel.refresh()
            }
        }

        binding.retryButton.apply {
            setOnClickListener {
                viewModel.retry()
            }
        }

        val questionsListAdapter = QuestionsListAdapter(this)

        binding.questionsList.apply {
            setHasFixedSize(true)
            binding.questionsList.adapter = questionsListAdapter
        }

        viewModel.questions.observe(this@QuestionsSearchFragment, Observer {
            questionsListAdapter.submitList(it)
        })

        viewModel.networkState.observe(this@QuestionsSearchFragment, Observer {
            questionsListAdapter.setNetworkState(it)
            binding.isLoading = it?.status == Status.FAILED
            if (it?.status == Status.FAILED) {
                binding.errorMsg.apply {
                    text = it.msg
                }
            }
        })

    }

    override fun onQuestionSelected(question: Question) {
        val intent = Intent(context, QuestionDetailActivity::class.java)
        intent.putExtra(QUESTION_LINK, question.link)
        startActivity(intent)

    }

    fun searchViewClicked(query: String?) {
        if (viewModel.setQuery(query)) {
            binding.questionsList.scrollToPosition(0)
            (binding.questionsList.adapter as? QuestionsListAdapter)?.submitList(null)
        }
        activity?.title = "#${viewModel.getTitle().value}"
    }


}