package com.grafschokula.stackquestions.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 *Created by Tim on 19, July, 2019
 **/
interface StackOverflowApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("questions?site=stackoverflow")
    fun getQuestionsByTag(@Query("tagged") tag: String,
                          @Query("page") page: Int,
                          @Query("pageSize") pageSize: Int): Single<StackResponse>
}