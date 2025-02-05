package com.example.a10_recyclerview

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // получаем ссылку на текстовое поле в каждом элементе списка
    val tv = itemView.findViewById<TextView>(R.id.color)

    // TODO: добавить обработку нажатия на элемент списка (вывести Toast с кодом цвета)
    // совет: использовать блок init { }
    init {
        itemView.setOnClickListener {
            val color = tv.currentTextColor
            val hexColor = String.format("#%06X", (0xFFFFFF and color))
            Toast.makeText(itemView.context, "Color: $hexColor", Toast.LENGTH_SHORT).show()
        }
    }

    fun bindTo(color: Int) {
        tv.setBackgroundColor(color)
        tv.setTextColor(color)
        // вывод кода цвета в 16-ричном виде
//        tv.text = String.format("#%06X", 0xFFFFFF and color)
    }
}