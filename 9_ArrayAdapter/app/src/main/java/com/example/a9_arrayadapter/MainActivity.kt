package com.example.a9_arrayadapter

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private val people = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lvPeople = findViewById<ListView>(R.id.people)
        val names: Array<String> = resources.getStringArray(R.array.name)
        names.shuffle()
        val surnames: Array<String> = resources.getStringArray(R.array.surname)
        surnames.shuffle()

        val min_size = min(surnames.size, names.size)

//        people = Array<String>(min_size) {i -> ""}
        for (i in 0 until min_size){
            people.add("${names[i]} ${surnames[i]}")
        }


        // TODO: сгенерировать список персон из случайных сочетаний имён и фамилий
        // TODO: создайте два string-array в ресурсах и получите список их случайных комбинаций

        val adapter = ArrayAdapter(this, R.layout.item, people)
        lvPeople.adapter = adapter // задаём адаптер (посредник) для отображения данных на списке

        // пример чтения строк из ресурсов
        val sampleList = resources.getStringArray(R.array.samplelist) // функция возвращает массив
    }

    fun onAddPersonClick(view: View) {
        val editText = findViewById<EditText>(R.id.new_name)
        val newName = editText.text.toString()
        if (newName.isNotBlank()) {
            people.add(newName)

            val lvPeople = findViewById<ListView>(R.id.people)
            (lvPeople.adapter as ArrayAdapter<String>).notifyDataSetChanged()

            editText.text.clear()
        }

        // TODO: реализовать добавление новых персон в список
        // имя считывать из текстового поля
        // если нужно изменять число элементов, используйте MutableList<String>
    }
}