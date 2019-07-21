package com.grafschokula.stackquestions.data.repository

import com.grafschokula.stackquestions.data.api.StackOverflowApi
import com.grafschokula.stackquestions.data.api.StackResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 *Created by Tim on 19, July, 2019
 **/
@Singleton
class QuestionsRemoteDataSource @Inject constructor(private val stackOverflowApi: StackOverflowApi){

    fun getQuestionsByTag(query: String, page: Int, pageSize: Int): Single<StackResponse> {
        return stackOverflowApi.getQuestionsByTag(query, page, pageSize)
    }
}