package com.ndefiorenze.weather.landing.view

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ndefiorenze.weather.landing.R
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
            val starter = baseIntent(URL_CAPITALS, activity)
            activity.startActivity(starter)
        }
        buttonCity.setOnClickListener{
            val starter = baseIntent(URL_CITY, activity)
            activity.startActivity(starter)
        }

    }

    private fun baseIntent(url: String, context: Context? = null): Intent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                .addCategory(Intent.CATEGORY_DEFAULT)
                .addCategory(Intent.CATEGORY_BROWSABLE)
        if (context != null) {
            intent.`package` = context.packageName
        }
        return intent
    }

    override fun onStop() {
        super.onStop()
        buttonCapitals.setOnClickListener(null)
        buttonCity.setOnClickListener(null)
    }

    companion object {
        private const val URL_BASE = "https://weather.ndefiorenze.com"
        private const val URL_LANDING = "$URL_BASE/landing"
        private const val URL_CAPITALS = "$URL_BASE/capitals"
        private const val URL_CITY = "$URL_BASE/city"
    }
}