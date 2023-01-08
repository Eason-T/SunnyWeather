package com.thoughtworks.sunnyweather.ui.weather


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.thoughtworks.sunnyweather.logic.model.Location
import com.thoughtworks.sunnyweather.logic.repo.WeatherRepo

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    private val weatherRepo = WeatherRepo()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        weatherRepo.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}