package com.example.a11_fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class CurrentTaskFragment: Fragment() {
    private val small_images = listOf(
        R.drawable.no_img,
        R.drawable.small_1,
        R.drawable.small_2
    )

    private var mainView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_current_task, container, false)
        mainView?.setBackgroundColor(Color.BLUE)

        val weather_id: Int? = arguments?.getInt("weather_id", -1)

        if (mainView != null){
            setWeather(weather_id)
        }

        return mainView
    }

     fun setWeather(weather_id: Int?){
        val smallWeatherImg: ImageView = mainView?.findViewById(R.id.smallWeather) ?: return;

        if ((weather_id != null) && (weather_id in 1 .. small_images.size - 1)){
            smallWeatherImg.setImageResource(small_images[weather_id]);
        } else {
            smallWeatherImg.setImageResource(small_images.last());
        }
    }
}