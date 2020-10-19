package com.example.poccacheapp.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.poccacheapp.R
import com.example.poccacheapp.databinding.CitiesFragmentBinding
import com.example.poccacheapp.databinding.PhotosFragmentBinding

class photosFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PhotosFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.photos_fragment, container, false
        )

        return binding.root
    }
}