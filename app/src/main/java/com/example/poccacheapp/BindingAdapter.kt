package com.example.poccacheapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.poccacheapp.data.Photos1
import com.example.poccacheapp.data.State
import com.example.poccacheapp.overview.OverviewAdapter
import com.example.poccacheapp.overview.StatesApiStatus
import com.example.poccacheapp.photos.PhotosAdapter
import com.example.poccacheapp.photos.PhotosApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<State>?){
    val adapter = recyclerView.adapter as OverviewAdapter
    adapter.submitList(data)
}
@BindingAdapter("listPhotosData")
fun bindPhotosRecyclerView(recyclerView: RecyclerView,
                     data: List<Photos1>?){
    val adapter = recyclerView.adapter as PhotosAdapter
    adapter.submitList(data)
}
/*
@BindingAdapter("stateImageUrl")
fun bindStateImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri =imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
*/
@BindingAdapter("cityImageUrl")
fun bindCityImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri =imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String){
    imgUrl.let {
        val imgUri =imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("stateApiStatus")
fun bindStatesStatus(statusStates:ImageView, status: StatesApiStatus){
    when(status){
        StatesApiStatus.LOADING -> {
            statusStates.visibility = View.VISIBLE
            statusStates.setImageResource(R.drawable.loading_animation)
        }
        StatesApiStatus.DONE -> {
            statusStates.visibility = View.GONE
        }
        StatesApiStatus.ERROR -> {
        statusStates.visibility = View.VISIBLE
        statusStates.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("photosApiStatus")
fun bindPhotosStatus(statusPhotos1:ImageView, status: PhotosApiStatus){
    when(status){
        PhotosApiStatus.LOADING -> {
            statusPhotos1.visibility = View.VISIBLE
            statusPhotos1.setImageResource(R.drawable.loading_animation)
        }
        PhotosApiStatus.DONE -> {
            statusPhotos1.visibility = View.GONE
        }
        PhotosApiStatus.ERROR -> {
            statusPhotos1.visibility = View.VISIBLE
            statusPhotos1.setImageResource(R.drawable.ic_connection_error)
        }
    }
}
