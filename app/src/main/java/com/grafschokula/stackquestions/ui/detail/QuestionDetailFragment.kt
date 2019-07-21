package com.grafschokula.stackquestions.ui.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.grafschokula.stackquestions.R
import com.grafschokula.stackquestions.databinding.FragmentQuestionDetailBinding
import com.grafschokula.stackquestions.di.module.ViewModelFactory
import com.grafschokula.stackquestions.di.qualifier.ActivityScoped
import com.grafschokula.stackquestions.ui.base.BaseFragment
import javax.inject.Inject

/**
 *Created by Tim on 20, July, 2019
 **/
@ActivityScoped
class QuestionDetailFragment @Inject constructor(): BaseFragment<FragmentQuestionDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: QuestionDetailViewModel


    override fun getLayoutResId() = R.layout.fragment_question_detail

    @Suppress("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val questionWebClient = QuestionWebViewClient()
        binding.questionWebView.settings.javaScriptEnabled = true
        binding.questionWebView.webViewClient = questionWebClient
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(QuestionDetailViewModel::class.java)
        displayQuestion()

    }

    private fun displayQuestion() {
        viewModel.getUrlLink().observe(this, Observer {
            binding.questionWebView.loadUrl(it)
        })
    }

    private class QuestionWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
    }
}