package com.thoughtworks.sunnyweather.ui.weather.route

import android.app.Activity

interface Route {
    fun navigateTo(activity: Activity)
}