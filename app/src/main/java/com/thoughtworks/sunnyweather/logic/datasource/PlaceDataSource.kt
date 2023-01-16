package com.thoughtworks.sunnyweather.logic.datasource

import androidx.lifecycle.liveData
import com.thoughtworks.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

class PlaceDataSource {
    fun searchPlace(query: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
                if (placeResponse.status == "ok") {
                    val places = placeResponse.places
                    Result.success(places)
                } else {
                    Result.failure(RuntimeException("response status is ${placeResponse.status}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
}