package com.thoughtworks.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.thoughtworks.sunnyweather.logic.Repository
import com.thoughtworks.sunnyweather.logic.model.Place

class PlaceViewModel constructor(private val repository: Repository) : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = repository.savePlace(place)

    fun getSavedPlace() = repository.getSavedPlace()

    fun isPlaceSaved() = repository.isPlaceSaved()
}