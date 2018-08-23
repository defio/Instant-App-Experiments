package com.ndefiorenze.weatherinstantappexample.capitals.data

sealed class CityWeather {

    data class Known(
            val city: String,
            val weatherCondition: WeatherCondition,
            val temperature: Float,
            val windSpeed: Float) : CityWeather()

    data class Unknown(val city: String) : CityWeather()
}