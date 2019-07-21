package com.grafschokula.stackquestions.data

import com.grafschokula.stackquestions.data.api.StackOverflowApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

/**
 *Created by Tim on 19, July, 2019
 **/
@Module
object ApiServiceModule {

    private const val BASE_URL = "https://api.stackexchange.com"

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.d(it)
        })
        logger.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder().apply {
            addInterceptor(logger)
        }.build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(client)
        }.build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideStackOverflowAPi(retrofit: Retrofit): StackOverflowApi = retrofit.create(StackOverflowApi::class.java)
}