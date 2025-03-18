package com.example.databindingtask

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherHandler(private val activity: MainActivity, private val weatherData: WeatherData) {

    fun onClick() {
        val city = activity.binding.cityInput.text.toString().trim()
        if (city.isEmpty()) {
            Toast.makeText(activity, "Введите название города", Toast.LENGTH_SHORT).show()
            return
        }

        GlobalScope.launch(Dispatchers.IO) {
            activity.loadWeather(city)
        }
    }
}
