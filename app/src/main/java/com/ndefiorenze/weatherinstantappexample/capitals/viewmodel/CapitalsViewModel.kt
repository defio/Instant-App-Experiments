package com.ndefiorenze.weatherinstantappexample.capitals.viewmodel

import android.arch.lifecycle.ViewModel
import com.ndefiorenze.weatherinstantappexample.capitals.data.repository.CapitalsRepository
import com.ndefiorenze.weatherinstantappexample.capitals.data.CityWeather
import com.ndefiorenze.weatherinstantappexample.capitals.data.repository.WeatherRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class CapitalsViewModel(private val weatherRepository: WeatherRepository,
                        private val capitalsRepository: CapitalsRepository) : ViewModel() {

    private val capitalsWeather = BehaviorSubject.create<List<CityWeather>>()

    init {
        fetchCapitalsWeather()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({ capitalsWeather.onNext(it) }, { capitalsWeather.onError(it) })
    }

    fun getCapitalsWeatherObservable(): Observable<List<CityWeather>> = capitalsWeather

    private fun fetchCapitalsWeather(): Single<List<CityWeather>> {
        return Observable.fromIterable(capitalsRepository.getCapitals())
                .flatMapSingle { getWeatherForCity(it) }
                .toList()
    }

    private fun getWeatherForCity(city: String) = weatherRepository.getWheaterForCity(city)
            .onErrorReturn { CityWeather.Unknown(city) }

}