package com.example.poccacheapp.photos

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.example.poccacheapp.data.Config
import com.example.poccacheapp.data.Photos1
import com.example.poccacheapp.data.api
import com.example.poccacheapp.network.AllApi
import com.example.poccacheapp.u2
import kotlinx.coroutines.launch

enum class PhotosApiStatus { LOADING, ERROR, DONE}


class PhotosViewModel(application: Application) : AndroidViewModel(application){

    private val _status = MutableLiveData<PhotosApiStatus>()
    val status: LiveData<PhotosApiStatus>
        get() = _status

    private val _photosProperties = MutableLiveData<List<Photos1>>()
    val photosProperties: LiveData<List<Photos1>>
        get() = _photosProperties

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getPhotos()
    }

    private fun getPhotos(){
        _status.value = PhotosApiStatus.LOADING
        val pref = getApplication<Application>().applicationContext.getSharedPreferences("APIs",Context.MODE_PRIVATE)
        val url1 = pref.getString("url2","")
        Log.d("ds",url1.toString())
        viewModelScope.launch {
            try {
                _photosProperties.value = AllApi.retrofitService.getPhotosProperties(url1.toString()).photos
                _status.value = PhotosApiStatus.DONE
            }
            catch (e: Exception){
                _status.value = PhotosApiStatus.ERROR
                //_response.value = "Failure: ${e.message}"
                _photosProperties.value = ArrayList()
            }
        }
    }
}