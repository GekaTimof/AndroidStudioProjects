package com.example.a23_get_data_from_sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.a23_get_data_from_sensors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sensorManager: SensorManager
    private var currentSensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        binding.radioGroupSensors.setOnCheckedChangeListener { _, checkedId ->
            val sensorType = when (checkedId) {
                R.id.radioAccelerometer -> Sensor.TYPE_ACCELEROMETER
                R.id.radioGyroscope -> Sensor.TYPE_GYROSCOPE
                else -> null
            }
            switchSensor(sensorType)
        }
    }

    private fun switchSensor(sensorType: Int?) {
        sensorManager.unregisterListener(this)
        sensorType?.let {
            currentSensor = sensorManager.getDefaultSensor(it)
            currentSensor?.let { sensor ->
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val values = it.values.joinToString(", ") { v -> "%.2f".format(v) }
            binding.textViewSensorData.text = "Sensor data: $values"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        currentSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
}
