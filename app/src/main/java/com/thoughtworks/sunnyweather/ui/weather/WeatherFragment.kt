package com.thoughtworks.sunnyweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoughtworks.sunnyweather.databinding.FragmentWeatherBinding
import com.thoughtworks.sunnyweather.logic.model.Place
import com.thoughtworks.sunnyweather.logic.model.Weather
import com.thoughtworks.sunnyweather.logic.model.getSky


class WeatherFragment : Fragment() {

    companion object {
        fun newInstance(): WeatherFragment {
            return WeatherFragment()
        }
    }

    val viewModel by lazy { ViewModelProvider(this)[WeatherViewModel::class.java] }

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

        val adapter = WeatherAdapter()
        val layoutManager = LinearLayoutManager(activity)
        binding.apply {
            weatherRecyclerView.layoutManager = layoutManager
            weatherRecyclerView.adapter = adapter
        }




    }
}