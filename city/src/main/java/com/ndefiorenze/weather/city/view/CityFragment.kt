package com.ndefiorenze.weather.city.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.ndefiorenze.weather.city.R
import com.ndefiorenze.weather.city.viewmodel.CityViewModel
import com.ndefiorenze.weather.city.viewmodel.CityViewModelFactory
import com.ndefiorenze.weatherinstantappexample.capitals.addTo
import com.ndefiorenze.weatherinstantappexample.capitals.data.toDescribableContent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.city_fragment.city_edit_text
import kotlinx.android.synthetic.main.city_fragment.progress_bar
import kotlinx.android.synthetic.main.city_fragment.result_textView
import kotlinx.android.synthetic.main.city_fragment.search_button


class CityFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var progressBar: ProgressBar
    private lateinit var resultTextView: TextView
    private lateinit var searchButton: Button
    private lateinit var cityEditText: EditText

    private val cityEditTextWatcher = CityEditTextWatcher()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.city_fragment, container, false)
    }


    override fun onStart() {
        super.onStart()

        progressBar = progress_bar
        resultTextView = result_textView
        searchButton = search_button
        cityEditText = city_edit_text

        val viewModel = ViewModelProviders.of(this, CityViewModelFactory()).get(CityViewModel::class.java)

        search_button.setOnClickListener {
            viewModel.fetchWeatherForCity(city_edit_text.text.toString())
        }

        cityEditText.addTextChangedListener(cityEditTextWatcher.apply {
            afterTextChanged = { text -> viewModel.newCandidateToFetch(text) }
        })

        observeMeteoResult(viewModel)
        observeSearchButtonState(viewModel)
        observeProgressLoading(viewModel)
    }

    private fun observeProgressLoading(viewModel: CityViewModel) {
        viewModel.getLoading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { loading ->
                    if (loading) {
                        progressBar.visibility = View.VISIBLE
                        resultTextView.text = ""
                    } else {
                        progressBar.visibility = View.INVISIBLE
                    }
                }
                .addTo(compositeDisposable)
    }

    private fun observeSearchButtonState(viewModel: CityViewModel) {
        viewModel.getFetchStatusObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { buttonEnabled ->
                    searchButton.isEnabled = buttonEnabled
                }
                .addTo(compositeDisposable)
    }

    private fun observeMeteoResult(viewModel: CityViewModel) {
        viewModel.getWeatherObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { cityWeather ->
                    result_textView.text = cityWeather.toDescribableContent()
                }.addTo(compositeDisposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()

        search_button.setOnClickListener(null)
        cityEditText.removeTextChangedListener(null)
    }

    class CityEditTextWatcher(var afterTextChanged: ((String) -> Unit)? = null) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged?.let { it(s.toString()) }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //nothing to do
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //nothing to do
        }
    }

}