package com.example.poccacheapp.cities

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.poccacheapp.data.City

class CityViewModelFactory(
    private val city: List<City>,
    private val application: Application) :ViewModelProvider.Factory{
    @Suppress("unchecker_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesViewModel::class.java)){
            return CitiesViewModel(city,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}