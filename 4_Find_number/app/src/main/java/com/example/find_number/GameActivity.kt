package com.example.find_number

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    var begin: Int = 0
    var end: Int = 100
    var center: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        begin = intent.getIntExtra("begin", 0)
        end = intent.getIntExtra("end", 100)

        Log.d("mytag", "begin = " + begin)
        Log.d("mytag", "end = " + end)

        // wrong start
        if (begin >= end){
            Log.d("mytag", "go ta EndActivity")
            val intent = Intent(this, EndActivity::class.java)
            intent.putExtra("res", "No range to make a prediction")
            startActivity(intent)
            finish()
        }

        center = begin.toDouble() + ((end - begin) / 2).toDouble() + 0.5
        Log.d("mytag", "Set new center - $center")

        val tvQuestion = findViewById<TextView>(R.id.question)
        tvQuestion.text = "Where is your number relative to $center?"
    }

    fun onUnswerClick(view: View) {
        val tvQuestion = findViewById<TextView>(R.id.question)

        when (view.id) {
            R.id.above -> {
                Log.d("mytag", "above")
//                tvQuestion.text = "yes"
                begin = (center + 1).toInt()
            }
            R.id.below -> {
                Log.d("mytag", "below")
//                tvQuestion.text = "no"
                end = center.toInt()
            }
        }

        center = begin.toDouble() + ((end - begin) / 2).toDouble() + 0.5
        Log.d("mytag", "Set new center - $center, begin - $begin, end - $end")

        if (begin == end){
            Log.d("mytag", "go ta EndActivity")
            val intent = Intent(this, EndActivity::class.java)
            intent.putExtra("res", "Your number is - $begin")
            startActivity(intent)
            finish()
        }

        tvQuestion.text = "Where is your number relative to $center?"
    }


}