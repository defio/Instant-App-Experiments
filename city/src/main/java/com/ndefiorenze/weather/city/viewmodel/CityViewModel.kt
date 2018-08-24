package com.ndefiorenze.weather.city.viewmodel

import android.arch.lifecycle.ViewModel
import com.ndefiorenze.weatherinstantappexample.capitals.addTo
import com.ndefiorenze.weatherinstantappexample.capitals.data.CityWeather
import com.ndefiorenze.weatherinstantappexample.capitals.data.repository.WeatherRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


class CityViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val cityWeather = BehaviorSubject.create<CityWeather>()

    private val fetching = BehaviorSubject.create<Boolean>()
    private val serchableCity = BehaviorSubject.create<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    init {
        fetching.onNext(false)
        serchableCity.onNext(false)
    }

    fun getWeatherObservable(): Observable<CityWeather> {
        return cityWeather
    }

    fun fetchWeatherForCity(city: String) {
        Single.fromCallable { Thread.sleep((Math.random() * 2000).toLong()) }
                .flatMap { weatherRepository.getWheaterForCity(city) }
                .doOnSubscribe { fetching.onNext(true) }
                .onErrorReturn { CityWeather.Unknown(city) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { _, _ -> fetching.onNext(false) }
                .subscribe { weather, _ -> cityWeather.onNext(weather) }
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun getFetchStatusObservable(): Observable<Boolean> {
        return Observable.combineLatest(fetching, serchableCity, BiFunction<Boolean, Boolean, Boolean> { fetching, serchableCity ->
            !fetching && serchableCity
        })
    }

    fun newCandidateToFetch(text: String) {
        serchableCity.onNext(text.isNotEmpty())
    }

    fun getLoading(): Observable<Boolean> = fetching
}

