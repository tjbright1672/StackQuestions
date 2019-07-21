package com.grafschokula.stackquestions.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grafschokula.stackquestions.di.qualifier.ViewModelKey
import com.grafschokula.stackquestions.ui.detail.QuestionDetailViewModel
import com.grafschokula.stackquestions.ui.search.QuestionsSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 *Created by Tim on 19, July, 2019
 **/
@Module
abstract class ViewModelModule {

    @Suppress("unused")
    @Binds
    @IntoMap
    @ViewModelKey(QuestionsSearchViewModel::class)
    abstract fun questionsSearchViewModel(viewModel: QuestionsSearchViewModel): ViewModel

    @Suppress("unused")
    @Binds
    @IntoMap
    @ViewModelKey(QuestionDetailViewModel::class)
    abstract fun questionDetailViewModel(viewModel: QuestionDetailViewModel): ViewModel

    @Suppress("unused")
    @Binds
    abstract fun viewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}