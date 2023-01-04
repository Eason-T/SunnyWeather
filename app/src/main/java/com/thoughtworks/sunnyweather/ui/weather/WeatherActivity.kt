package com.thoughtworks.sunnyweather.ui.weather

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.sunnyweather.R
import com.thoughtworks.sunnyweather.databinding.ActivityWeatherBinding
import com.thoughtworks.sunnyweather.logic.model.Weather
import com.thoughtworks.sunnyweather.logic.model.getSky
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val swipeRefresh = binding.swipeRefresh
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val navigationButton = findViewById<Button>(R.id.navigationButton)
        navigationButton.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {}
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {}
            override fun onDrawerClosed(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE)
                        as InputMethodManager
                manager.hideSoftInputFromWindow(
                    drawerView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        })
        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }

        viewModel.weatherLiveData.observe(this) { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "无法成功获取天气信息", Toast.LENGTH_LONG).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            swipeRefresh.isRefreshing = false
        }
        swipeRefresh.setColorSchemeResources(R.color.teal_200)
        refreshWeather(swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            refreshWeather(swipeRefresh)
        }
    }

    fun refreshWeather(swipeRefresh: SwipeRefreshLayout) {
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
        swipeRefresh.isRefreshing = true
    }

    private fun showWeatherInfo(weather: Weather) {
        val placeName = findViewById<TextView>(R.id.placeName)
        placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        //now.xml
        val currentTemp = findViewById<TextView>(R.id.currentTemp)
        val currentSky = findViewById<TextView>(R.id.currentSky)
        val currentAQI = findViewById<TextView>(R.id.currentAQI)
        val nowLayout = findViewById<RelativeLayout>(R.id.nowLayout)
        val currentTempText = "${realtime.temperature.toInt()}°C"
        currentTemp.text = currentTempText
        currentSky.text = getSky(realtime.skycon).info
        realtime.airQuality.api?.let {
            val currentPM25Text = "Air Quality ${realtime.airQuality.api.chn.toInt()}"
            currentAQI.text = currentPM25Text
        }
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        //forecast.xml
        val forecastLayout = findViewById<LinearLayout>(R.id.forecastLayout)
        forecastLayout.removeAllViews()
        val days = daily.skycon.size
        for (i in 0 until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view =
                LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateInfo = view.findViewById<TextView>(R.id.dateInfo)
            val skyInfo = view.findViewById<TextView>(R.id.skyInfo)
            val skyIcon = view.findViewById<ImageView>(R.id.skyIcon)
            val temperatureInfo = view.findViewById<TextView>(R.id.temperatureInfo)
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateInfo.text = simpleDateFormat.format(skycon.date)
            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min} ~ ${temperature.max}°C"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }
        //life index
        val lifeIndex = daily.lifeIndex
        val coldRiskText = findViewById<TextView>(R.id.coldRiskText)
        coldRiskText.text = lifeIndex.coldRisk[0].desc
        val dressingText = findViewById<TextView>(R.id.dressingText)
        dressingText.text = lifeIndex.dressing[0].desc
        val ultravioletText = findViewById<TextView>(R.id.ultravioletText)
        ultravioletText.text = lifeIndex.ultraviolet[0].desc
        val carWashingText = findViewById<TextView>(R.id.carWashingText)
        carWashingText.text = lifeIndex.carWashing[0].desc
        val weatherLayout = findViewById<ScrollView>(R.id.weatherLayout)
        weatherLayout.visibility = View.VISIBLE
    }
}