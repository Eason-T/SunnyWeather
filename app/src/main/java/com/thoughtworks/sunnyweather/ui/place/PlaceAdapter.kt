package com.thoughtworks.sunnyweather.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.sunnyweather.R
import com.thoughtworks.sunnyweather.logic.model.Place
import com.thoughtworks.sunnyweather.ui.weather.WeatherActivity

class PlaceAdapter(private val fragment: PlaceFragment, private val placeList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.placeName)
        val placeAddress: TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.place_item, parent, false)
        val holder = ViewHolder(view)
//        holder.itemView.setOnClickListener {
//            val position = holder.bindingAdapterPosition
//            val place = placeList[position]
//            val activity = fragment.activity
//            if (activity is WeatherActivity) {
//                activity.apply {
//                    findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawers()
//                    viewModel.locationLng = place.location.lng
//                    viewModel.locationLat = place.location.lat
//                    viewModel.placeName = place.name
//                    refreshWeather(swipeRefresh = findViewById(R.id.swipeRefresh))
//                }
//            } else {
//                val intent = Intent(parent.context, WeatherActivity::class.java).apply {
//                    putExtra("location_lng", place.location.lng)
//                    putExtra("location_lat", place.location.lat)
//                    putExtra("place_name", place.name)
//                }
//                fragment.startActivity(intent)
//                activity?.finish()
//            }
//            fragment.viewModel.savePlace(place)
//        }
        holder.itemView.setOnClickListener {
            fragment.viewModel.navigateToWeather()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address.toString()
    }

    override fun getItemCount() = placeList.size
}
