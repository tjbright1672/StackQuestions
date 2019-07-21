package com.grafschokula.stackquestions.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

/**
 *Created by Tim on 19, July, 2019
 **/
abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity() {

    @LayoutRes
    protected abstract fun getLayResId(): Int

    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayResId())
    }
}