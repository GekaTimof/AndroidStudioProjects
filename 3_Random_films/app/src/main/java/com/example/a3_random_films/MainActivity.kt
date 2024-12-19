package com.example.a3_random_films

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a3_random_films.Randomizer

class MainActivity : AppCompatActivity() {
    private lateinit var current_film: TextView
    private lateinit var counter: TextView

    private lateinit var films_arr:  Array<String>
    private lateinit var films_list: List<String>
    private lateinit var films_randomizer: Randomizer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        current_film = findViewById(R.id.film)
        counter = findViewById(R.id.counter)

        films_arr = resources.getStringArray(R.array.movies)
        films_list = films_arr.toList()

        films_randomizer = Randomizer(films_arr = films_list)
    }


    fun onClickNext(v: View? = null){
        current_film.text = films_randomizer.nextFilm()
        counter.text = films_randomizer.leftPosition()
    }

    fun onClickToStart(v: View){
        films_randomizer.randomize()
        onClickNext(v)
    }
}