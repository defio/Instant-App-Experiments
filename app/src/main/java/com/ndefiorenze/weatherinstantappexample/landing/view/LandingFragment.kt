package com.ndefiorenze.weatherinstantappexample.landing.view

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ndefiorenze.weatherinstantappexample.R
import com.ndefiorenze.weatherinstantappexample.capitals.view.CapitalsActivity
import com.ndefiorenze.weatherinstantappexample.city.view.CityActivity
import kotlinx.android.synthetic.main.landing_fragment.button_capitals
import kotlinx.android.synthetic.main.landing_fragment.button_city

class LandingFragment : Fragment() {

    private lateinit var buttonCapitals: Button
    private lateinit var buttonCity: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.landing_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        buttonCapitals = button_capitals
        buttonCity = button_city

        buttonCapitals.setOnClickListener{
            startActivity(Intent(this@LandingFragment.activity, CapitalsActivity::class.java))
        }
        buttonCity.setOnClickListener{
            startActivity(Intent(this@LandingFragment.activity, CityActivity::class.java))
        }

    }

    override fun onStop() {
        super.onStop()
        buttonCapitals.setOnClickListener(null)
        buttonCity.setOnClickListener(null)
    }
}