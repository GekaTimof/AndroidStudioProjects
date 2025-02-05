package com.example.a10_recyclerview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //val planetsList = arrayListOf<String>("Mars", "Venus", "Earth")
    // TODO: реализовать генерацию цветов определённой палитры


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorsList = mutableListOf(
            ContextCompat.getColor(this, R.color.palette_red),
            ContextCompat.getColor(this, R.color.palette_blue),
            ContextCompat.getColor(this, R.color.palette_green),
            ContextCompat.getColor(this, R.color.palette_yellow)
        )

        // пример использования RecyclerView с собственным адаптером
        val rv = findViewById<RecyclerView>(R.id.rview)
        val colorAdapter = ColorAdapter(LayoutInflater.from(this))
        // добавляем данные в список для отображения
        colorAdapter.submitList(colorsList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = colorAdapter
    }
}