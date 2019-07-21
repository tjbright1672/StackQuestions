package com.grafschokula.stackquestions.di.qualifier

import javax.inject.Scope

/**
 *Created by Tim on 19, July, 2019
 **/
@Scope
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScoped