package com.ndefiorenze.weather.capitals.data.repository

class CapitalsRepositoryImpl : CapitalsRepository {

    override fun getCapitals(): List<String> {
        return listOf("Amsterdam", "Berlin", "Madrid", "Rome", "London", "Paris", "Prague", "Stockholm", "Vienna")
    }

}