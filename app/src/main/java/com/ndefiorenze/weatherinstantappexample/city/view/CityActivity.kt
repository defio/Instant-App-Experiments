package com.ndefiorenze.weatherinstantappexample.city.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ndefiorenze.weatherinstantappexample.R

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Search Weather for city"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
