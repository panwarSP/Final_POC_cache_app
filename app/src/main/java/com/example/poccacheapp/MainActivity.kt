package com.example.poccacheapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poccacheapp.data.State
import com.example.poccacheapp.network.AllApi
import kotlinx.coroutines.coroutineScope


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


/*
        val pref = application.getSharedPreferences("APIs", Context.MODE_PRIVATE)
        u1= pref.getString("url1","").toString()
        u2 = pref.getString("url2","").toString()

        //S1 = AllApi.retrofitService.getStatesProperties(u1).States

        u1 = i.getStringExtra("URL1").toString()
        u2 = i.getStringExtra("URL2").toString()
*/