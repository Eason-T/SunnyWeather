package com.thoughtworks.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

const val SUNNY_TOKEN = "GssKYi260xzP5pXI"

class SunnyWeatherApplication : Application() {
    companion object {

        const val TOKEN = SUNNY_TOKEN

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}