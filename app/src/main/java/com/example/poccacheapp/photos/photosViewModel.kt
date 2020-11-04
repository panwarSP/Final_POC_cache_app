package com.example.poccacheapp.photos

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.example.poccacheapp.data.BasePhotos
import com.example.poccacheapp.data.BaseStates
import com.example.poccacheapp.data.Photos1
import com.example.poccacheapp.data.State
import com.example.poccacheapp.network.AllApi
import com.example.poccacheapp.overview.StatesApiStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type

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
        val pref = getApplication<Application>().applicationContext.getSharedPreferences(
            "APIs",
            Context.MODE_PRIVATE
        )
        val url2 = pref.getString("url2", "")
        val tu2 = pref.getString("tempurl2", "")
        val v2 = (pref.getString("version2", "")).toString()
        val tv2 = (pref.getString("tempv2", "")).toString()
        val editor = pref.edit()
        val endpoint2 = tu2?.substring(50)
        viewModelScope.launch {
            try {
                if(tv2.toDouble() > v2.toDouble()) {
                    val s = AllApi.retrofitService.getPhotosProperties(endpoint2.toString()).photos
                    _photosProperties.value = s
                    //val store2 = s.toString()

                    val json : List<Photos1> = s
                    val gson = Gson()
                    var store = gson.toJson(json)
                    store = "{photos: ${store}}"
                    Log.d("value of store",store)
                    editor.putString("photosapi", store)
                    editor.putString("url2", tu2.toString())
                    editor.putString("version2", tv2.toString())
                    //val p1 = pref.getString("stateapi", "")
                    editor.apply()
                    editor.commit()
                    _status.value = PhotosApiStatus.DONE
                }
                else{
                    val result : String? = pref.getString("photosapi", "")
                    Log.d("value of result", result.toString())
                    //val result1 = Gson().toJson(result)
                    val type: Type = object : TypeToken<BasePhotos?>() {}.type
                    val a: BasePhotos = Gson().fromJson(result, type)
                    Log.d("value of a",a.toString())
                    //val test : State = gson.fromJson(apistates, State::class.java) //parsing JSON string to JAVA Object
                    //var convertedObject: JsonObject = Gson().fromJson(apistates, JsonObject::class.java)
                    _photosProperties.value = a.photos
                    _status.value = PhotosApiStatus.DONE
                }
            }
            catch (e: Exception){
                _status.value = PhotosApiStatus.ERROR
                //_response.value = "Failure: ${e.message}"
                _photosProperties.value = ArrayList()
            }
        }
    }
}