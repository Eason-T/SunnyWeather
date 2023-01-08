package com.thoughtworks.sunnyweather.logic.repo

import com.thoughtworks.sunnyweather.logic.datasource.WeatherDataSource

class WeatherRepo {
    private val weatherDataSource = WeatherDataSource()

    fun refreshWeather(lng: String, lat: String) = weatherDataSource.getWeather(lng, lat)

}