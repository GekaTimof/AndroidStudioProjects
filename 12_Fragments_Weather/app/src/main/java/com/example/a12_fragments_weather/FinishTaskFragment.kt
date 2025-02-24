package com.example.a12_fragments_weather

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class FinishTaskFragment: Fragment() {
    private val big_images = listOf(
        R.drawable.no_img,
        R.drawable.big_1,
        R.drawable.big_2,
        R.drawable.big_3,
        R.drawable.big_4,
        R.drawable.big_5,
        R.drawable.big_6,
        R.drawable.big_7
    )

    private var mainView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainView = inflater.inflate(R.layout.fragment_finish_task, container, false)
        mainView?.setBackgroundColor(Color.YELLOW)

        val weather_id: Int? = arguments?.getInt("weather_id", -1)

        if (mainView != null){
            setWeather(weather_id)
        }

        return mainView
    }

    fun setWeather(weather_id: Int?){
        val smallWeatherImg: ImageView = mainView?.findViewById(R.id.bigWeather) ?: return;

        if ((weather_id != null) && (weather_id in 1 .. big_images.size - 1)){
            smallWeatherImg.setImageResource(big_images[weather_id]);
        } else {
            smallWeatherImg.setImageResource(big_images.last());
        }
    }
}
