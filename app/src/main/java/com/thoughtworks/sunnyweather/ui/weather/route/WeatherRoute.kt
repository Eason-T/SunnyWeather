package com.thoughtworks.sunnyweather.ui.weather.route

import android.app.Activity
import com.thoughtworks.sunnyweather.MainActivity
import com.thoughtworks.sunnyweather.ui.weather.WeatherFragment


object WeatherRoute : Route {
   override fun navigateTo(activity: Activity) {
        if (activity is MainActivity) {
            activity.addFragmentToBackStack(
                WeatherFragment.newInstance()
            )
        }
    }
}