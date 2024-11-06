package com.muratdayan.weather.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.muratdayan.weather.core.common.Resource
import com.muratdayan.weather.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.util.Locale

// handle the UI and business logic of the HomeFragment
@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    // binding usage in fragment
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var locationRequest: LocationRequest
    private val homeViewModel: HomeViewModel by viewModels()

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){permissions->
        val allGranted = permissions.values.all { it }
        if (allGranted){
            homeViewModel.requestLocation(requireContext())
        }else{
            Toast.makeText(requireContext(),"Permission denied",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

/*

        if (checkPermissions()) {
            getLocation()
        } else {
            requestPermissions()
        }
*/

        checkAndRequestPermissions()
        //observeLocationUpdates()
        collectProductState()


        return binding.root
    }

    private fun checkAndRequestPermissions() {
        if (homeViewModel.checkLocationPermissions(requireContext())) {
            homeViewModel.requestLocation(requireContext())
        } else {
            permissionLauncher.launch(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
            )
        }
    }

    private fun collectProductState() {

        lifecycleScope.launch {
            homeViewModel.currentForecastState.collectLatest { currentForecast->
                when (currentForecast) {
                    is Resource.Error ->{
                        Toast.makeText(requireContext(),currentForecast.msg,Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Idle ->{}
                    is Resource.Loading -> {
                        Toast.makeText(requireContext(),"Loading",Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        binding.textViewDegree.text = currentForecast.data?.current?.temperature.toString()
                    }
                }

            }
        }

    }


    // if the fragment is destroyed, the binding object is set to null
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}