package com.thoughtworks.sunnyweather.ui.weather

import android.view.ViewGroup
import com.thoughtworks.sunnyweather.ui.util.BaseItemAdapter
import com.thoughtworks.sunnyweather.ui.util.EmptyViewHolder
import com.thoughtworks.sunnyweather.ui.util.ItemViewHolder
import com.thoughtworks.sunnyweather.ui.util.ItemViewModel
import com.thoughtworks.sunnyweather.ui.weather.item.RealtimeViewHolder
import com.thoughtworks.sunnyweather.ui.weather.item.RealtimeViewModel

class WeatherAdapter : BaseItemAdapter<ItemViewHolder<ItemViewModel>>() {
    companion object {
        private const val ITEM_REALTIME_WEATHER = 1
        private const val EMPTY_ITEM = -1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<ItemViewModel> =
        when (viewType) {
            ITEM_REALTIME_WEATHER -> RealtimeViewHolder(parent)
            else -> EmptyViewHolder(parent)
        }


    override fun getItemViewType(position: Int): Int  =
        when (getItem(position)) {
            is RealtimeViewModel -> ITEM_REALTIME_WEATHER
            else -> EMPTY_ITEM
        }

}