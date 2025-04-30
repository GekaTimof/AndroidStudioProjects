package com.example.a22_sensors_list


import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SensorListAdapter

    sealed class SensorListItem {
        data class Header(val title: String) : SensorListItem()
        data class SensorItem(val sensor: Sensor) : SensorListItem()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.sensorRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val allSensors = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val items = buildGroupedSensorList(allSensors)
        adapter = SensorListAdapter(items)
        recyclerView.adapter = adapter
    }

    private fun buildGroupedSensorList(allSensors: List<Sensor>): List<SensorListItem> {
        val result = mutableListOf<SensorListItem>()

        fun addGroup(title: String, types: List<Int>) {
            val groupSensors = allSensors.filter { it.type in types }
            if (groupSensors.isNotEmpty()) {
                result.add(SensorListItem.Header(title))
                result.addAll(groupSensors.map { SensorListItem.SensorItem(it) })
            }
        }

        addGroup("Environmental sensors", listOf(
            Sensor.TYPE_AMBIENT_TEMPERATURE,
            Sensor.TYPE_LIGHT,
            Sensor.TYPE_PRESSURE,
            Sensor.TYPE_RELATIVE_HUMIDITY
        ))

        addGroup("Device position sensors", listOf(
            Sensor.TYPE_ACCELEROMETER,
            Sensor.TYPE_GYROSCOPE,
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_GRAVITY,
            Sensor.TYPE_ROTATION_VECTOR
        ))

        addGroup("Human condition sensors", listOf(
            Sensor.TYPE_HEART_RATE,
            Sensor.TYPE_STEP_COUNTER,
            Sensor.TYPE_STEP_DETECTOR
        ))

        return result
    }

    fun getSensorListItems(): List<SensorListItem> {
        return buildGroupedSensorList(sensorManager.getSensorList(Sensor.TYPE_ALL))
    }
}