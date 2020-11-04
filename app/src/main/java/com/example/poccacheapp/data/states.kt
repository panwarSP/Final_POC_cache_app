package com.example.poccacheapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseStates(
    val States: List<State>
): Parcelable

@Parcelize
data class State(
    val id: String,
    val Name: String,
    val State_Image: String,
    val Cities: List<City>
):Parcelable

@Parcelize
data class City(
    val City_name: String,
    val STD_Code: String,
    val City_Image: String,
    val City_weather: City_weather1,
    val main: Main
):Parcelable

@Parcelize
data class City_weather1(
    val id: String,
    val weather: String,
    val description: String
):Parcelable
@Parcelize
data class Main(
    val temprature: Int,
    val pressure: Int,
    val humidity: Int
):Parcelable


