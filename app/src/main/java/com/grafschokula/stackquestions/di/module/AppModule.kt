package com.grafschokula.stackquestions.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 *Created by Tim on 19, July, 2019
 **/
@Module
abstract class AppModule {

    @Suppress("unused")
    @Binds
    abstract fun bindContext(application: Application): Context

}