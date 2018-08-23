package com.ndefiorenze.weatherinstantappexample.capitals.data.repository

import com.ndefiorenze.weatherinstantappexample.capitals.data.CityWeather
import io.reactivex.Single

interface WeatherRepository {

    fun getWheaterForCity(city: String): Single<CityWeather>
}
