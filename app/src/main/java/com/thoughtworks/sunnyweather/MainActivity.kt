package com.thoughtworks.sunnyweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addFragmentToBackStack(fragment: Fragment){
        supportFragmentManager.beginTransaction().addToBackStack("${fragment}").replace(R.id.weatherFragment,fragment)
    }


}