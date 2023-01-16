package com.thoughtworks.sunnyweather.ui.weather.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thoughtworks.sunnyweather.R
import com.thoughtworks.sunnyweather.ui.util.ItemViewHolder

class RealtimeViewHolder(parent: ViewGroup) : ItemViewHolder<RealtimeViewModel>(
    LayoutInflater.from(parent.context).inflate(R.layout.now, parent, false)
) {
    override fun bind(item: RealtimeViewModel) {
        TODO("Not yet implemented")
    }

}