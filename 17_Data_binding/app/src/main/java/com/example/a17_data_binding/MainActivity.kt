package com.example.a17_data_binding

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.a17_data_binding.databinding.ActivityMainBinding
import com.google.gson.Gson
import kotlinx.coroutines.*
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val apiKey = ""
    private var selectedCity = "Irkutsk"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.handler = this
        setupSpinner()
    }

    private fun setupSpinner() {
        val cities = arrayOf("Irkutsk", "Moscow", "Novosibirsk")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)
        binding.citySpinner.adapter = adapter
        binding.citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedCity = cities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun loadWeather() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val urlString = "https://api.openweathermap.org/data/2.5/weather?q=$selectedCity&appid=$apiKey&units=metric"
                val url = URL(urlString)
                val urlConnection = url.openConnection() as HttpURLConnection

                try {
                    // Set timeout parameters
                    urlConnection.connectTimeout = 5000
                    urlConnection.readTimeout = 5000

                    val responseCode = urlConnection.responseCode
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        val response = urlConnection.inputStream.use { stream ->
                            Gson().fromJson(InputStreamReader(stream), WeatherResponse::class.java)
                        }

                        withContext(Dispatchers.Main) {
                            // Выводим в лог для отладки
                            Log.d("Weather", "Temperature: ${response.main.temp}°C")
                            binding.weather = response
                        }
                    } else {
                        throw Exception("Error fetching weather: Response code $responseCode")
                    }
                } finally {
                    urlConnection.disconnect()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Weather", "Error fetching weather", e)
                }
            }
        }
    }
}

data class WeatherResponse(
    val weather: List<WeatherCondition>,
    val main: Main
)

data class WeatherCondition(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Float
)
