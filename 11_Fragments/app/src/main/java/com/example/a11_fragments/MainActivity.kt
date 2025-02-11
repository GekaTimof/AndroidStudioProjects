package com.example.a11_fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    lateinit var ft: FragmentTransaction
    lateinit var fr1: Fragment
    lateinit var fr2: Fragment
    lateinit var toFinishTask: Button
    lateinit var toCurrentTask: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        ft = fm.beginTransaction()
        // set fragment 2
        fr2 = FinishTaskFragment()

        // get fragments container
        val fr = fm.findFragmentById(R.id.container_fragm)
        if (fr == null) {
            // set fragment 1
            fr1 = CurrentTaskFragment()
            fm.beginTransaction().add(R.id.container_fragm, fr1)
                .commit()
        } else
            fr1 = fr

        // get buttons
        toCurrentTask = findViewById(R.id.toCurrentTask)
        toFinishTask = findViewById(R.id.toFinishTask)

        toFinishTask.setOnClickListener {
            // set weather parameter
            val bundle = Bundle()
            bundle.putInt("weather_id", 2)

            fr2.arguments = bundle

            // do transaction
            val ft = fm.beginTransaction()
            ft.replace(R.id.container_fragm, fr2)
            ft.commit()
        }

        toCurrentTask.setOnClickListener {
            // set weather parameter
            val bundle = Bundle()
            bundle.putInt("weather_id", 2)

            fr1.arguments = bundle

            // do transaction
            val ft = fm.beginTransaction()
            ft.replace(R.id.container_fragm, fr1)
            ft.commit()
        }
    }

}