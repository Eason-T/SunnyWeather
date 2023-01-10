package com.thoughtworks.sunnyweather.ui.util

import android.view.View
import android.view.ViewGroup

class EmptyViewHolder(parent: ViewGroup) : ItemViewHolder<ItemViewModel>(View(parent.context)) {
    override fun bind(items: ItemViewModel) {}
}