package com.example.poccacheapp.networking

data class Base_photos(
    val photos: List<Photos1>
)

data class Detail(
    val user_id: Number,
    val name: String,
    val image: Image
)

data class Image(
    val url: String,
    val title: String,
    val description: String
)

data class Photos1(
    val id: Number,
    val detail: Detail
)