package com.grafschokula.stackquestions.di.component

import android.app.Application
import com.grafschokula.stackquestions.StackApp
import com.grafschokula.stackquestions.di.module.ActivityBindingModule
import com.grafschokula.stackquestions.data.ApiServiceModule
import com.grafschokula.stackquestions.di.module.AppModule
import com.grafschokula.stackquestions.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 *Created by Tim on 19, July, 2019
 **/
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ApiServiceModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<StackApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }
}