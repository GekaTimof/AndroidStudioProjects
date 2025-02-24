package com.example.a12_fragments_weather

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.a12_fragments_weather.CurrentTaskFragment
import com.example.a12_fragments_weather.FinishTaskFragment
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

data class WeatherCondition(val id: Int, val main: String, val description: String, val icon: String)
data class WeatherResponse (val weather: List<WeatherCondition>, val sys: Sys, val main: Main)
class Sys (val sunset: Int, val sunrise: Int)
class Main (val temp: Float, val feels_like: Float)


class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment
    lateinit var toFinishTask: Button
    lateinit var toCurrentTask: Button

    private val API_KEY = ""
    private val cities = arrayOf("Irkutsk", "Moscow", "Novosibirsk")

    fun getWeatherID(city: String): Int{
        val weather_url =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$API_KEY"

        return try {
            val url = URL(weather_url)
            val stream = url.getContent() as InputStream
            val gson = Gson()

            val response: WeatherResponse =
                gson.fromJson(InputStreamReader(stream), WeatherResponse::class.java)
            val result: Int = response.weather.firstOrNull()?.id ?: -1

            when (result) {
                in 200..232 ->  1
                in 300..321 ->  2
                in 500..531 ->  3
                in 600..622 ->  4
                in 701..781 ->  5
                800 ->  6
                in 802..804 ->  7
                else ->  0
            }
        }catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        ft = fm.beginTransaction()
        // set fragment 2
        fr2 = FinishTaskFragment()

        // get fragments container
        val fr = fm.findFragmentById(R.id.container_fragm)
        if (fr == null) {
            // set fragment 1
            fr1 = CurrentTaskFragment()
            fm.beginTransaction().add(R.id.container_fragm, fr1)
                .commit()
        } else
            fr1 = fr

        // get buttons
        toCurrentTask = findViewById(R.id.toCurrentTask)
        toFinishTask = findViewById(R.id.toFinishTask)

        toFinishTask.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                // set weather parameter
                val bundle = Bundle()
                bundle.putInt("weather_id", getWeatherID(cities[0]))

                fr2.arguments = bundle

                // do transaction
                val ft = fm.beginTransaction()
                ft.replace(R.id.container_fragm, fr2)
                ft.commit()
            }
        }

        toCurrentTask.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                // set weather parameter
                val bundle = Bundle()
                bundle.putInt("weather_id", getWeatherID(cities[0]))

                fr1.arguments = bundle

                // do transaction
                val ft = fm.beginTransaction()
                ft.replace(R.id.container_fragm, fr1)
                ft.commit()
                }
        }
    }

}
