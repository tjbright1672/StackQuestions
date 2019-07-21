package com.grafschokula.stackquestions.ui.detail

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.grafschokula.stackquestions.R
import com.grafschokula.stackquestions.databinding.ActivityQuestionDetailBinding
import com.grafschokula.stackquestions.di.module.ViewModelFactory
import com.grafschokula.stackquestions.ui.base.BaseActivity
import com.grafschokula.stackquestions.ui.search.QuestionsSearchFragment.Companion.QUESTION_LINK
import com.grafschokula.stackquestions.utils.addFragmentToActivity
import javax.inject.Inject

/**
 *Created by Tim on 20, July, 2019
 **/
class QuestionDetailActivity : BaseActivity<ActivityQuestionDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var questionDetailFragment: QuestionDetailFragment

    private lateinit var viewModel: QuestionDetailViewModel

    override fun getLayResId() = R.layout.activity_question_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val urLink: String = intent.getStringExtra(QUESTION_LINK)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(QuestionDetailViewModel::class.java)

        viewModel.setUrlLink(urLink)

        addFragmentToActivity(R.id.fragment_container, questionDetailFragment)

    }
}