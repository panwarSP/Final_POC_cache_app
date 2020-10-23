package com.example.poccacheapp.network

import com.example.poccacheapp.data.BasePhotos
import com.example.poccacheapp.data.BaseStates
import com.example.poccacheapp.data.Config
import com.example.poccacheapp.data.api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://run.mocky.io/v3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface ApiService {

    @GET("7e32f9c7-06f7-4bca-9795-ca6b4703d046")
    suspend fun getStatesProperties(): BaseStates

    @GET("6c5bba13-35a8-4e59-97b1-2e38d5966b15")
    suspend fun getPhotosProperties(): BasePhotos

}


/*
interface StateApiService {

}
interface PhotosApiService {

}
*/
object AllApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
/*
object StateApi{
    val retrofitService: StateApiService by lazy {
        retrofit.create(StateApiService::class.java)
    }
    @GET("6c5bba13-35a8-4e59-97b1-2e38d5966b15")
    suspend fun getPhotosProperties(): BasePhotos
}
object PhotosApi{
    val retrofitService: PhotosApiService by lazy {
        retrofit.create(PhotosApiService::class.java)
    }
}
*/



