package com.example.poccacheapp.photos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.poccacheapp.R
import com.example.poccacheapp.databinding.PhotosFragmentBinding

class photosFragment : Fragment() {

    private val photoViewModel: PhotosViewModel by lazy {
        ViewModelProvider(this).get(PhotosViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = PhotosFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.photosViewModel =photoViewModel

        binding.recyclerView.adapter = PhotosAdapter()

        return binding.root
    }
}