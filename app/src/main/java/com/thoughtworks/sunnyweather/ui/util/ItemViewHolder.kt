package com.thoughtworks.sunnyweather.ui.util

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewHolder<out T : ItemViewModel>(view: View) : RecyclerView.ViewHolder(view) {

    protected val context: Context = itemView.context
    abstract fun bind(item: @UnsafeVariance T)
    open fun detach() = run { }
}