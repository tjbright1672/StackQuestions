package com.grafschokula.stackquestions.ui.detail

import com.grafschokula.stackquestions.di.qualifier.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *Created by Tim on 20, July, 2019
 **/
@Module
abstract class QuestionDetailModule {

    @Suppress("unused")
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun questionDetailFragment(): QuestionDetailFragment

}