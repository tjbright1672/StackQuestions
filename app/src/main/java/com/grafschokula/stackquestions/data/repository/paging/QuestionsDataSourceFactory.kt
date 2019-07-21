package com.grafschokula.stackquestions.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.grafschokula.stackquestions.data.model.Question
import com.grafschokula.stackquestions.data.repository.QuestionsRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Tim on 19, July, 2019
 **/
class QuestionsDataSourceFactory(
    private val questionsRemoteDataSource: QuestionsRemoteDataSource,
    private val disposable: CompositeDisposable,
    private val query: String
) : DataSource.Factory<Int, Question>() {

    private val _sourceLiveData = MutableLiveData<QuestionsDataSource>()
    val sourceLiveData
        get() = _sourceLiveData

    override fun create(): DataSource<Int, Question> {
        val questionsDataSource = QuestionsDataSource(questionsRemoteDataSource, disposable, query)
        _sourceLiveData.postValue(questionsDataSource)
        return questionsDataSource
    }

}