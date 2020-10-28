package com.example.poccacheapp.overview

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccacheapp.MainActivity
import com.example.poccacheapp.Util.SplashActivity
import com.example.poccacheapp.data.State
import com.example.poccacheapp.network.AllApi
import com.example.poccacheapp.u1
import kotlinx.coroutines.launch


enum class StatesApiStatus { LOADING, ERROR, DONE}
class OverviewViewmodel: ViewModel() {
    private val _status = MutableLiveData<StatesApiStatus>()

    // The external immutable LiveData for the response String
    val status: LiveData<StatesApiStatus>
        get() = _status

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _properties1 = MutableLiveData<List<State>>()
    val properties1: LiveData<List<State>>
        get() = _properties1

    private val _navigateToSelectedProperty = MutableLiveData<State>()
    val navigateToSelectedProperty: LiveData<State>
        get() = _navigateToSelectedProperty

    init {
        getStates()
    }

    private fun getStates(){
        _status.value = StatesApiStatus.LOADING
        viewModelScope.launch {
            try {
                _properties1.value = AllApi.retrofitService.getStatesProperties(u1).States
                _status.value = StatesApiStatus.DONE
            }
            catch (e: Exception) {
                _response.value = "Failure: ${e.message} + ${u1}"
                _status.value = StatesApiStatus.ERROR
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

