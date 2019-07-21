package com.grafschokula.stackquestions.ui.search

import android.text.format.DateUtils
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.grafschokula.stackquestions.GlideApp
import com.grafschokula.stackquestions.R
import de.hdodenhof.circleimageview.CircleImageView

/**
 *Created by Tim on 19, July, 2019
 **/
object QusetionsSearchBindingAdapters {

    @JvmStatic
    @BindingAdapter("imageFromUrl")
    fun bindImageFromUrl(circleImageView: CircleImageView, urlString: String?) {
        GlideApp.with(circleImageView.context)
            .load(urlString)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_image)
            .into(circleImageView)
    }

    @JvmStatic
    @BindingAdapter("text")
    fun bindText(textView: TextView, time: Long) {
        textView.text = DateUtils.getRelativeTimeSpanString(time * 1000, System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS).toString()
    }

    @JvmStatic
    @BindingAdapter("isGone")
    fun View.isGone(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }
}