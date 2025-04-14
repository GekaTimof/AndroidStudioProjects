package com.example.a20_zhdun

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var timeReceiver: BroadcastReceiver
    private lateinit var batteryReceiver: BroadcastReceiver
    private lateinit var textView: TextView
    private lateinit var button: Button

    private var minutesPassed = 0
    private var isTimeRegistered = true

    companion object {
        const val toast_text = "Ждун ушёл по делам!"
        const val TIME_TICK = "android.intent.action.TIME_TICK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        timeReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == TIME_TICK && isTimeRegistered) {
                    minutesPassed++
                    textView.text = "время созерцания: $minutesPassed мин."
                }
            }
        }

        batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == Intent.ACTION_BATTERY_LOW) {
                    textView.text = "накормите Ждуна, силы на исходе!"
                }
            }
        }

        registerReceiver(timeReceiver, IntentFilter(TIME_TICK))
        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_LOW))

        button.setOnClickListener {
            if (isTimeRegistered) {
                unregisterReceiver(timeReceiver)
                isTimeRegistered = false
                Toast.makeText(this, toast_text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        if (isTimeRegistered) {
            unregisterReceiver(timeReceiver)
        }
        unregisterReceiver(batteryReceiver)
        super.onDestroy()
    }
}