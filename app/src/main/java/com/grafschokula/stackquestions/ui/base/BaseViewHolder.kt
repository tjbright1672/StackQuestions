package com.grafschokula.stackquestions.ui.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *Created by Tim on 19, July, 2019
 **/
abstract class BaseViewHolder<out T : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding: T = DataBindingUtil.bind(itemView)!!
}