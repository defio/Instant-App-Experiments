package com.ndefiorenze.weatherinstantappexample.capitals.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.ndefiorenze.weatherinstantappexample.R
import com.ndefiorenze.weatherinstantappexample.capitals.data.CityWeather

class CitiesAdapter(var dataSet: List<CityWeather> = emptyList()) : RecyclerView.Adapter<CityWeatherCellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherCellViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView

        return CityWeatherCellViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CityWeatherCellViewHolder, position: Int) {
        holder.textView.text = dataSet[position].toDescribableContent()
    }

}

private fun CityWeather.toDescribableContent(): String {
    return when (this) {
        is CityWeather.Known -> this.toDescribableContent()
        is CityWeather.Unknown -> "Unknown weather"
    }

}

private fun CityWeather.Known.toDescribableContent(): String {
    return """
       City: $city
       Weather Condition: $weatherCondition,
       Temperature: $temperature °C,
       Wind Speed: ${windSpeed.toInt()} km/h
   """.trimIndent()

}
