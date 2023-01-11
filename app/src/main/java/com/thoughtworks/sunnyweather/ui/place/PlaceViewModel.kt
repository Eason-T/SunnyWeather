package com.thoughtworks.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.thoughtworks.sunnyweather.logic.model.Place
import com.thoughtworks.sunnyweather.logic.repo.PlaceRepo
import com.thoughtworks.sunnyweather.ui.weather.route.Route
import com.thoughtworks.sunnyweather.ui.weather.route.WeatherRoute


class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    private val _navigator = MutableLiveData<Route>()
    val navigator = _navigator
    private val placeRepo = PlaceRepo()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        placeRepo.getPlace(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = placeRepo.savePlace(place)

    fun getSavedPlace() = placeRepo.getSavedPlace()

    fun isPlaceSaved() = placeRepo.isPlaceSaved()

    fun navigateToWeather(){
        navigator.value = WeatherRoute
    }
}