package com.grafschokula.stackquestions.ui.search

import com.grafschokula.stackquestions.di.qualifier.FragmentScoped
import com.grafschokula.stackquestions.ui.search.QuestionsSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *Created by Tim on 19, July, 2019
 **/
@Module
abstract class QuestionsModule {

    @Suppress("unused")
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun questionsSearchFragment(): QuestionsSearchFragment

}