package com.ndefiorenze.weatherinstantappexample.capitals.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ndefiorenze.weatherinstantappexample.R
import com.ndefiorenze.weatherinstantappexample.capitals.addTo
import com.ndefiorenze.weatherinstantappexample.capitals.viewmodel.CapitalsViewModel
import com.ndefiorenze.weatherinstantappexample.capitals.viewmodel.CapitalsViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CapitalsFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.capitals_fragment, container, false)

        recyclerView = view.findViewById(R.id.capitals_recycler_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CapitalsFragment.context)
            adapter = CitiesAdapter()
        }
        return view
    }


    override fun onStart() {
        super.onStart()
        val viewModel = ViewModelProviders.of(this, CapitalsViewModelFactory()).get(CapitalsViewModel::class.java)

        viewModel.getCapitalsWeatherObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { citiesWeather ->
                    (recyclerView.adapter as CitiesAdapter).apply {
                        dataSet = citiesWeather
                        notifyDataSetChanged()
                    }
                }.addTo(compositeDisposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

}