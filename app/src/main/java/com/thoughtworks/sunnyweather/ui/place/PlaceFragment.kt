package com.thoughtworks.sunnyweather.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thoughtworks.sunnyweather.MainActivity
import com.thoughtworks.sunnyweather.databinding.FragmentPlaceBinding
import com.thoughtworks.sunnyweather.ui.weather.WeatherActivity

class PlaceFragment : Fragment() {
    val viewModel by lazy { ViewModelProvider(this)[PlaceViewModel::class.java] }
    private lateinit var _binding: FragmentPlaceBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PlaceAdapter(this, viewModel.placeList)
        val layoutManager = LinearLayoutManager(activity)

        if (activity is MainActivity && viewModel.isPlaceSaved()) {
            val place = viewModel.getSavedPlace()
            val intent = Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng", place.location.lng)
                putExtra("location_lat", place.location.lat)
                putExtra("place_name", place.name)
            }
            startActivity(intent)
            activity?.finish()
            return //todo
        }

        binding.apply {
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter

            searchPlaceEdit.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    val content = query.toString()
                    if (content.isNotEmpty()) {
                        viewModel.searchPlaces(content)
                    } else {
                        recyclerView.visibility = View.GONE
                        bgImageView.visibility = View.VISIBLE
                        viewModel.placeList.clear()
                        adapter.notifyDataSetChanged() //todo refactor
                    }
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })

            viewModel.placeLiveData.observe(viewLifecycleOwner) { result ->
                val place = result.getOrNull()
                if (place != null) {
                    recyclerView.visibility = View.VISIBLE
                    bgImageView.visibility = View.GONE
                    viewModel.placeList.clear()
                    viewModel.placeList.addAll(place)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_LONG).show()
                    result.exceptionOrNull()?.printStackTrace()
                }
            }
        }
    }
}