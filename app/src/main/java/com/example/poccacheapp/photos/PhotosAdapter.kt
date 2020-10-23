package com.example.poccacheapp.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.poccacheapp.data.Photos1
import com.example.poccacheapp.databinding.ListviewPhotosBinding

class PhotosAdapter : androidx.recyclerview.widget.ListAdapter<Photos1, PhotosAdapter.PhotosPropertyViewHolder>(DiffCallback) {

    class PhotosPropertyViewHolder(private var binding: ListviewPhotosBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photos1: Photos1) {
            binding.photosProperty = photos1
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Photos1>() {
        override fun areItemsTheSame(oldItem: Photos1, newItem: Photos1): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photos1, newItem: Photos1): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PhotosPropertyViewHolder {
        return PhotosPropertyViewHolder(ListviewPhotosBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotosPropertyViewHolder, position: Int) {
        val photosProperty = getItem(position)
        holder.bind(photosProperty)
    }
}