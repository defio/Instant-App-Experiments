package com.ndefiorenze.weatherinstantappexample.data

fun CityWeather.toDescribableContent(): String {
    return when (this) {
        is CityWeather.Known -> this.toDescribableContent()
        is CityWeather.Unknown -> "Unknown weather for $city"
    }

}

fun CityWeather.Known.toDescribableContent(): String {
    return """
       City: $city
       Weather Condition: $weatherCondition,
       Temperature: $temperature °C,
       Wind Speed: ${windSpeed.toInt()} km/h
   """.trimIndent()

}