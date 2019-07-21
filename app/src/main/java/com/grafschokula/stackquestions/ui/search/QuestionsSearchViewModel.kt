package com.grafschokula.stackquestions.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.grafschokula.stackquestions.data.repository.QuestionsRemoteDataSource
import com.grafschokula.stackquestions.data.repository.paging.QuestionsPagingRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *Created by Tim on 19, July, 2019
 **/
class QuestionsSearchViewModel @Inject constructor(
    private val questionsRemoteDataSource: QuestionsRemoteDataSource
) : ViewModel() {

    private val query = MutableLiveData<String>()
    private val disposable = CompositeDisposable()
    private val default = "android"

    init {
        query.value = default
    }

    private val questionsListing by lazy {
        map(query) {
            QuestionsPagingRepository(
                questionsRemoteDataSource,
                disposable
            ).getQuestionsByTag(it, 20)
        }
    }

    private val _questions = switchMap(questionsListing) { it.pagedList }
    val questions
        get() = _questions

    private val _networkState = switchMap(questionsListing) { it.networkState }
    val networkState
        get() = _networkState

    private val _refreshState = switchMap(questionsListing) { it.refreshState }
    val refreshState
        get() = _refreshState

    fun refresh() = questionsListing.value?.refresh?.invoke()
    fun retry() = questionsListing.value?.retry?.invoke()

    fun getTitle(): LiveData<String> = query

    fun setQuery(query: String?): Boolean{
        if (this.query.value == query) {
            return false
        }
        this.query.value = query
        return true
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}