package com.thoughtworks.sunnyweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thoughtworks.sunnyweather.databinding.FragmentWeatherBinding
import com.thoughtworks.sunnyweather.logic.model.Weather
import com.thoughtworks.sunnyweather.logic.model.getSky


class WeatherFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.weatherLiveData.observe(viewLifecycleOwner) { result ->
//            val weather = result.getOrNull()
//            if (weather != null) {
//                showWeatherInfo(weather)
//            } else {
//                Toast.makeText(context, "无法成功获取天气信息", Toast.LENGTH_LONG).show()
//                result.exceptionOrNull()?.printStackTrace()
//            }
//        }
//        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)

    }
//
//    private fun showWeatherInfo(weather: Weather) {
//        val realtime = weather.realtime
//        val daily = weather.daily
//        val currentTempText = "${realtime.temperature.toInt()}"
//        binding.nowLayout.apply {
//            placeName.text = viewModel.placeName
//            currentTemp.text = currentTempText
//            currentSky.text = getSky(realtime.skycon).info
//            realtime.airQuality.api?.let {
//                val currentPM25Text = "Air Quality ${realtime.airQuality.api.chn.toInt()}"
//                currentAQI.text = currentPM25Text
//            }
//            nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
//        }
//    }


}