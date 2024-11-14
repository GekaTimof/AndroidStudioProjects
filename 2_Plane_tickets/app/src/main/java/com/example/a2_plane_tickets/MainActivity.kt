package com.example.a2_plane_tickets

import android.widget.Spinner
import android.widget.ArrayAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val departureSpinner: Spinner = findViewById(R.id.spinnerDepartureCity)
        val arrivalSpinner: Spinner = findViewById(R.id.spinnerArrivalCity)

        // Получаем массив городов из ресурсов
        val cityList = resources.getStringArray(R.array.city_list)

        // Создаем адаптер для спиннеров
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Присваиваем адаптер к спиннерам
        departureSpinner.adapter = adapter
        arrivalSpinner.adapter = adapter
    }





}