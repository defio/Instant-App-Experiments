package com.ndefiorenze.weatherinstantappexample.capitals.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ndefiorenze.weatherinstantappexample.R

class CapitalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.capitals_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "European capital's Weather"
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
