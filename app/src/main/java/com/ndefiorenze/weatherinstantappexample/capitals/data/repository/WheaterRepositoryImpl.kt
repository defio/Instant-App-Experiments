package com.ndefiorenze.weatherinstantappexample.capitals.data.repository

import com.ndefiorenze.weatherinstantappexample.capitals.data.CityWeather
import com.ndefiorenze.weatherinstantappexample.capitals.data.WeatherCondition
import io.reactivex.Single
import java.util.Random

class WheaterRepositoryImpl : WeatherRepository {

    override fun getWheaterForCity(city: String): Single<CityWeather> {
        if (isWeatherKnownForCity(city)) {
            return fetchWeatherForCity(city)
        }
        return getUnknownWeatherForCity(city)
    }

    private fun getUnknownWeatherForCity(city: String): Single<CityWeather> = Single.just(CityWeather.Unknown(city))

    private fun fetchWeatherForCity(city: String): Single<CityWeather> {
        return Single.just(CityWeather.Known(city = city,
                weatherCondition = WeatherCondition.values().getRandomItem(),
                temperature = (-15 until 30).random().toFloat(),
                windSpeed = (0 until 40).random().toFloat()))
    }

    private fun isWeatherKnownForCity(city: String): Boolean {
        return Math.random() < 0.9f
    }

    private fun <T> Array<T>.getRandomItem(): T {
        return this[(0 until size).random()]
    }

    private fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive + 1) - start) + start

}