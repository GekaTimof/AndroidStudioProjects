package com.example.a17_data_binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("weatherIcon")
fun loadWeatherIcon(view: ImageView, iconCode: String?) {
    iconCode?.let {
        val url = "https://openweathermap.org/img/wn/$it@2x.png"
        Glide.with(view.context).load(url).into(view)
    }
}
