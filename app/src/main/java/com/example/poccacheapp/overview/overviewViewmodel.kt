package com.example.poccacheapp.overview
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.poccacheapp.data.BaseStates
import com.example.poccacheapp.data.State
import com.example.poccacheapp.network.AllApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.reflect.Type


enum class StatesApiStatus { LOADING, ERROR, DONE}
class OverviewViewmodel(application: Application) : AndroidViewModel(application) {
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
        val pref = getApplication<Application>().applicationContext.getSharedPreferences(
            "APIs",
            Context.MODE_PRIVATE
        )
        val url1 = pref.getString("url1", "")
        val tu1 = pref.getString("tempurl1", "")
        val v1 = (pref.getString("version1", "")).toString()
        val tv1 = (pref.getString("tempv1", "")).toString()
        val editor = pref.edit()
        Log.d("ds", tu1.toString())
        val endpoint1 = tu1?.toString()?.substring(50)
        Log.d("ds", endpoint1.toString())
        Log.d("ds", url1.toString())
        viewModelScope.launch {
            try {
                if(tv1.toDouble() > v1.toDouble()) {
                    val s = AllApi.retrofitService.getStatesProperties(endpoint1.toString()).States
                    _properties1.value = s
                    //val store2 = s.toString()

                    val json : List<State> = s
                    val gson = Gson()
                    var store = gson.toJson(json)
                    store = "{States: ${store}}"
                    Log.d("value of store",store)
                    editor.putString("statesapi", store)
                    editor.putString("url1", tu1.toString())
                    editor.putString("version1", tv1.toString())
                    //val p1 = pref.getString("stateapi", "")
                    editor.apply()
                    editor.commit()
                    _status.value = StatesApiStatus.DONE
                }
                else{
                    val result : String? = pref.getString("statesapi", "")
                    Log.d("value of result", result.toString())
                    //val result1 = Gson().toJson(result)
                    val type: Type = object : TypeToken<BaseStates?>() {}.type
                    val a: BaseStates = Gson().fromJson(result, type)
                    Log.d("value of a",a.toString())
                    //val test : State = gson.fromJson(apistates, State::class.java) //parsing JSON string to JAVA Object
                    //var convertedObject: JsonObject = Gson().fromJson(apistates, JsonObject::class.java)
                    _properties1.value = a.States
                    _status.value = StatesApiStatus.DONE
                }
                //_status.value = StatesApiStatus.DONE
            }
            catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
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

