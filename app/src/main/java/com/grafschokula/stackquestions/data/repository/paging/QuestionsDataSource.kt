package com.grafschokula.stackquestions.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.grafschokula.stackquestions.data.api.StackResponse
import com.grafschokula.stackquestions.data.model.Question
import com.grafschokula.stackquestions.data.repository.NetworkState
import com.grafschokula.stackquestions.data.repository.QuestionsRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 *Created by Tim on 19, July, 2019
 **/
class QuestionsDataSource(
    private val questionsRemoteDataSource: QuestionsRemoteDataSource,
    private val disposable: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Int, Question>() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState
        get() = _networkState

    private val _initialLoad = MutableLiveData<NetworkState>()
    val initialLoad
        get() = _initialLoad

    private var retryCompletable: Completable? = null

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        // not implemented on appending to the initial load
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Question>) {
        // for refresh


        _networkState.postValue(NetworkState.LOADING)
        _initialLoad.postValue(NetworkState.LOADING)

        disposable.add(
            getQuestions(1, params.requestedLoadSize)
                .subscribe({
                    setRetry(null)
                    _networkState.postValue(NetworkState.LOADED)
                    _initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(it.questions, null, 2)
                }, {
                    setRetry(Action { loadInitial(params, callback) })
                    onError("Error loadInitial(): ${it.localizedMessage}")
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Question>) {
        _networkState.postValue(NetworkState.LOADING)

        disposable.add(getQuestions(params.key, params.requestedLoadSize)
            .subscribe({
                setRetry(null)
                _networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.questions, params.key + 1)

            }, {
                setRetry(Action { loadAfter(params, callback) })
                onError("Error loadAfter(): ${it.localizedMessage}")
            })
        )
    }

    fun retry() {
        if (retryCompletable != null) {
            disposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({}, { Timber.e(it.localizedMessage) })
            )
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            retryCompletable = null
        } else {
            retryCompletable = Completable.fromAction(action)
        }
    }

    private fun onError(message: String) {
        val error = NetworkState.error(message)
        _networkState.postValue(error)
        _initialLoad.postValue(error)
    }

    private fun getQuestions(page: Int, pageSize: Int): Single<StackResponse> {
        Timber.d("$query")
        if (query.isNotEmpty()) {
            return questionsRemoteDataSource.getQuestionsByTag(query, page, pageSize)
        }
        throw RuntimeException("Query cannot be empty")
    }
}