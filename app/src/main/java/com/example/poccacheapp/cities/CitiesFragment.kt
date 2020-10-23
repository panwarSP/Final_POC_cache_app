package com.example.poccacheapp.cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.poccacheapp.databinding.CitiesFragmentBinding


class CitiesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = CitiesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val city = CitiesFragmentArgs.fromBundle(arguments!!).selectedProperty.Cities
        val viewModelFactory = CityViewModelFactory(city, application)
        binding.cityViewModel = ViewModelProvider(this, viewModelFactory).get(CitiesViewModel::class.java)
        return binding.root
    }
}