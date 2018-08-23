package com.ndefiorenze.weatherinstantappexample.capitals.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ndefiorenze.weatherinstantappexample.capitals.data.repository.CapitalsRepositoryImpl
import com.ndefiorenze.weatherinstantappexample.capitals.data.repository.WheaterRepositoryImpl


internal class CapitalsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CapitalsViewModel::class.java)) {
            return createCapitalsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private fun createCapitalsViewModel(): CapitalsViewModel {
        return CapitalsViewModel(
                WheaterRepositoryImpl(),
                CapitalsRepositoryImpl()
        )
    }
}