package com.example.find_number

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onGuessClick(view: View) {
        Log.d("mytag", "go ta GameActivity")

        val intent = Intent(this, GameActivity::class.java)
        val begin :Int = findViewById<EditText>(R.id.begin).text.toString().toInt()
        val end: Int = findViewById<EditText>(R.id.end).text.toString().toInt()
        intent.putExtra("begin", begin)
        intent.putExtra("end", end)
        startActivity(intent)
        finish()
    }
}