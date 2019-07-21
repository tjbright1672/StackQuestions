package com.grafschokula.stackquestions.data.repository.paging

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.grafschokula.stackquestions.data.model.Question
import com.grafschokula.stackquestions.data.repository.Listing
import com.grafschokula.stackquestions.data.repository.QuestionsRemoteDataSource
import com.grafschokula.stackquestions.data.repository.QuestionsRepository
import io.reactivex.disposables.CompositeDisposable

/**
 *Created by Tim on 19, July, 2019
 **/
class QuestionsPagingRepository (
    private val questionsRemoteDataSource: QuestionsRemoteDataSource,
    private val disposable: CompositeDisposable
) : QuestionsRepository {

    override fun getQuestionsByTag(query: String, pageSize: Int): Listing<Question> {

        val questionsDataSourceFactory = QuestionsDataSourceFactory(questionsRemoteDataSource, disposable, query)



        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(40)
            .build()

        val livePagedList =
            LivePagedListBuilder(questionsDataSourceFactory, config).build()

        val networkState = Transformations.switchMap(questionsDataSourceFactory.sourceLiveData) {
            it.networkState
        }

        val refreshState = Transformations.switchMap(questionsDataSourceFactory.sourceLiveData) {
            it.initialLoad
        }


        return Listing(
            livePagedList,
            networkState,
            refreshState,
            refresh = { questionsDataSourceFactory.sourceLiveData.value?.invalidate() },
            retry = { questionsDataSourceFactory.sourceLiveData.value?.retry() }
        )
    }
}