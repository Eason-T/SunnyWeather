package com.thoughtworks.sunnyweather.ui.util

import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemAdapter<T : ItemViewHolder<ItemViewModel>> : RecyclerView.Adapter<T>() {
    private val items: MutableList<ItemViewModel> = mutableListOf()

    override fun onBindViewHolder(holder: T, position: Int) {
        TODO("Not yet implemented")
    }

    fun getItem(position: Int): ItemViewModel? {
        return items.getOrNull(position)
    }


    override fun getItemCount() = items.size
}