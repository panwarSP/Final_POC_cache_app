package com.example.poccacheapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccacheapp.data.State
import com.example.poccacheapp.network.AllApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus { LOADING, ERROR, DONE}
class OverviewViewmodel: ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<ApiStatus>
        get() = _status

    private val _properties1 = MutableLiveData<List<State>>()
    val properties1: LiveData<List<State>>
        get() = _properties1

    private val _navigateToSelectedProperty = MutableLiveData<State>()
    val navigateToSelectedProperty: LiveData<State>
        get() = _navigateToSelectedProperty

    init {
        getStates()
    }

    fun getStates(){
        _status.value = ApiStatus.LOADING
        viewModelScope.launch {
            try {
                _properties1.value = AllApi.retrofitService.getStatesProperties().States

                //_properties2.value = AllApi.retrofitService.getStatesProperties().States.Uttarpradesh
                _status.value = ApiStatus.DONE
            }
            catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _properties1.value = ArrayList()
            }
        }
    }

    fun displayCities(states: State){
        _navigateToSelectedProperty.value = states
    }
    fun displayCitiesComplete(){
        _navigateToSelectedProperty.value = null
    }
}