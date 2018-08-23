package com.ndefiorenze.weatherinstantappexample.capitals.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ndefiorenze.weatherinstantappexample.R
import com.ndefiorenze.weatherinstantappexample.capitals.viewmodel.CapitalsViewModel
import com.ndefiorenze.weatherinstantappexample.capitals.viewmodel.CapitalsViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CapitalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}
