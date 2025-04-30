package com.example.a22_sensors_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.View

class SensorListAdapter(
    private val items: List<MainActivity.SensorListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_SENSOR = 1
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(android.R.id.text1)
    }

    class SensorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sensorText: TextView = view.findViewById(android.R.id.text1)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MainActivity.SensorListItem.Header -> VIEW_TYPE_HEADER
            is MainActivity.SensorListItem.SensorItem -> VIEW_TYPE_SENSOR
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder(view)
        } else {
            SensorViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is MainActivity.SensorListItem.Header -> (holder as HeaderViewHolder).titleText.text = item.title
            is MainActivity.SensorListItem.SensorItem -> (holder as SensorViewHolder).sensorText.text = item.sensor.name
        }
    }

    override fun getItemCount() = items.size
}