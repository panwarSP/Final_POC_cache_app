package com.example.poccacheapp.cities

import android.app.Application
import androidx.lifecycle.*
import com.example.poccacheapp.data.City
import com.example.poccacheapp.data.State


class CitiesViewModel(@Suppress("UNUSED_PARAMETER")city: List<City>,app:Application)
    :AndroidViewModel(app){
    private val _selectedProperty = MutableLiveData<List<City>>()
    val selectedProperty: LiveData<List<City>>
        get() = _selectedProperty

    private val _selectedProperty2 = MutableLiveData<List<City>>()
    val selectedProperty2: LiveData<List<City>>
        get() = _selectedProperty2
    init {
        _selectedProperty.value = city
        _selectedProperty2.value = city
    }


    val displayCityName = Transformations.map(selectedProperty2){
        it[0].City_name
    }
    val displayTemp = Transformations.map(selectedProperty2){
        it[0].main.temprature.toString()
    }

    val displaySTD = Transformations.map(selectedProperty2){
        it[0].STD_Code
    }
    val displayCity_weather1 = Transformations.map(selectedProperty2){
        it[0].City_weather.weather
    }

    val displayCityName2 = Transformations.map(selectedProperty2){
        it[1].City_name
    }
    val displayTemp2 = Transformations.map(selectedProperty2){
        it[1].main.temprature.toString()
    }

    val displaySTD2 = Transformations.map(selectedProperty2){
        it[1].STD_Code
    }
    val displayCity_weather12 = Transformations.map(selectedProperty2){
        it[1].City_weather.weather
    }
}