package com.example.poccacheapp.data

data class BasePhotos(
    val photos: List<Photos1>
)

data class Photos1(
    val id: String,
    val detail: Detail
)

data class Detail(
    val user_id: String,
    val name: String,
    val image: Image
)

data class Image(
    val url: String,
    val title: String,
    val description: String
)
