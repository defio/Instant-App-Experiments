package com.ndefiorenze.weather.city.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ndefiorenze.weatherinstantappexample.capitals.data.repository.WheaterRepositoryImpl


internal class CityViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return createCapitalsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun createCapitalsViewModel(): CityViewModel {
        return CityViewModel(
                WheaterRepositoryImpl()
        )
    }
}