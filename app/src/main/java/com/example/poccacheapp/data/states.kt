package com.example.poccacheapp.networking

data class Base(
    val States: States
)
data class States(
    val Rajasthan: List<Rajasthan1>,
    val Uttarpradesh: List<Uttarpradesh1>
)
data class Rajasthan1(
    val City: String,
    val STD_Code: String,
    val City_Image: String,
    val city_weather: List<City_weather1>,
    val main: Main
)

data class Uttarpradesh1(
    val City: String,
    val STD_Code: String,
    val City_Image: String,
    val city_weather: List<City_weather1>,
    val main: Main
)

data class City_weather1(
    val id: Int,
    val main: String,
    val description: String
)

data class Main(
    val temp: Int,
    val pressure: Int,
    val humidity: Int
)


