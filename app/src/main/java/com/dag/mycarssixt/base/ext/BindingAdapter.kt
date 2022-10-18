package com.dag.mycarssixt.base.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dag.mycarssixt.component.toolbar.TextOrRes

@BindingAdapter("app:visible")
fun View?.visible(value: Any?) = this?.run {
    visibility = when {
        value is List<*> && value.isNotEmpty() -> View.VISIBLE
        value is String && value.isNotBlank() -> View.VISIBLE
        value is Boolean -> if (value) View.VISIBLE else View.GONE
        value is Int && (value == View.VISIBLE || value == View.INVISIBLE || value == View.GONE) -> value
        value != null -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("app:textOrRes")
fun TextView.setTextOrRes(textOrRes: TextOrRes?) {
    textOrRes?.let {
        when {
            textOrRes.first != null -> text = textOrRes.first
            textOrRes.second != null && textOrRes.second != 0 -> setText(textOrRes.second!!)
        }
    }
}

@BindingAdapter("android:src")
fun ImageView.setImageViewResource(resource: Int?) {
    if (resource != null && resource != 0) {
        setImageResource(resource)
    }
}