package com.grafschokula.stackquestions.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 *Created by Tim on 20, July, 2019
 **/
class QuestionDetailViewModel @Inject constructor() : ViewModel() {

    private val urlLink = MutableLiveData<String>()

    fun setUrlLink(urlLink: String) {
        this.urlLink.value = urlLink
    }

    fun getUrlLink(): LiveData<String> = urlLink

}