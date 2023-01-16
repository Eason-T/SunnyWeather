package com.thoughtworks.sunnyweather.logic.repo

import com.thoughtworks.sunnyweather.logic.dao.PlaceDao
import com.thoughtworks.sunnyweather.logic.datasource.PlaceDataSource
import com.thoughtworks.sunnyweather.logic.model.Place

class PlaceRepo {
    private val placeDataSource = PlaceDataSource() //todo test canstr

    fun getPlace(query: String) = placeDataSource.searchPlace(query)

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}