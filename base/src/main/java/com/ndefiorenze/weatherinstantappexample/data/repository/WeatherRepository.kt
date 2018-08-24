package com.ndefiorenze.weatherinstantappexample.data.repository

import com.ndefiorenze.weatherinstantappexample.data.CityWeather
import io.reactivex.Single

interface WeatherRepository {

    fun getWheaterForCity(city: String): Single<CityWeather>
}
