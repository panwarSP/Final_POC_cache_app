package com.example.poccacheapp.overview

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.poccacheapp.MainActivity
import com.example.poccacheapp.R
import com.example.poccacheapp.Util.SplashActivity
import com.example.poccacheapp.data.Main
import com.example.poccacheapp.databinding.ListViewStatesBinding
import com.example.poccacheapp.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewmodel by lazy {
        ViewModelProvider(this).get(OverviewViewmodel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = OverviewFragmentBinding.inflate(inflater)

        binding.lifecycleOwner  = this

        binding.viewModel =viewModel
        binding.recyclerviewStates.adapter =
            OverviewAdapter(OverviewAdapter.OnClickListener {
                viewModel.displayCities(it)
            })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if( null != it){
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                viewModel.displayCitiesComplete()
            }
        })

        binding.photosButton.setOnClickListener {
            findNavController().navigate(OverviewFragmentDirections.actionOverviewFragmentToPhotosFragment())
        }
        return binding.root
    }
}