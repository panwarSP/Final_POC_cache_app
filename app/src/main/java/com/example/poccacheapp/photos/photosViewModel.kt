package com.example.poccacheapp.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccacheapp.data.Config
import com.example.poccacheapp.data.Photos1
import com.example.poccacheapp.data.api
import com.example.poccacheapp.network.AllApi
import kotlinx.coroutines.launch

enum class PhotosApiStatus { LOADING, ERROR, DONE}

class PhotosViewModel: ViewModel(){

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
        viewModelScope.launch {
            try {
                _photosProperties.value = AllApi.retrofitService.getPhotosProperties().photos
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