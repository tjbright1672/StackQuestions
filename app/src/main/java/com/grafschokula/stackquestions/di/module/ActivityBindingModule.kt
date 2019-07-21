package com.grafschokula.stackquestions.di.module

import com.grafschokula.stackquestions.di.qualifier.ActivityScoped
import com.grafschokula.stackquestions.ui.detail.QuestionDetailActivity
import com.grafschokula.stackquestions.ui.detail.QuestionDetailModule
import com.grafschokula.stackquestions.ui.search.QuestionsModule
import com.grafschokula.stackquestions.ui.search.QuestionsSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *Created by Tim on 19, July, 2019
 **/
@Module
abstract class ActivityBindingModule {

    @Suppress("unused")
    @ActivityScoped
    @ContributesAndroidInjector(modules = [QuestionsModule::class])
    abstract fun questionsSearchActivity(): QuestionsSearchActivity

    @Suppress("unused")
    @ActivityScoped
    @ContributesAndroidInjector(modules = [QuestionDetailModule::class])
    abstract fun questionDetailActivity(): QuestionDetailActivity

}