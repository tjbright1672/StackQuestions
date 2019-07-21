package com.grafschokula.stackquestions.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

/**
 *Created by Tim on 19, July, 2019
 **/
abstract class BaseFragment<T: ViewDataBinding> : DaggerFragment() {

    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    protected lateinit var binding: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false).apply {
            binding = this
        }.root
    }


}