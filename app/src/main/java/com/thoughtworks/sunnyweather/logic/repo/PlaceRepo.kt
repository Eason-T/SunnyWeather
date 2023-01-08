package com.thoughtworks.sunnyweather.logic.repo

import com.thoughtworks.sunnyweather.logic.datasource.PlaceDataSource

class PlaceRepo {
    private val placeDataSource = PlaceDataSource()

    fun getPlace(query: String) = placeDataSource.searchPlace(query)
}