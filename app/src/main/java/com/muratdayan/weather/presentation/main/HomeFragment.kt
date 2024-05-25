package com.muratdayan.weather.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // fragmentlarda binding kullanımı
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var locationRequest: LocationRequest
    private val homeViewModel: HomeViewModel by viewModels()

    private var locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // binding bağlanması
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        if (checkPermissions()) {
            getLocation()
        } else {
            requestPermissions()
        }


        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun collectProductState() {
        lifecycleScope.launch {
            homeViewModel.currentWeatherState.collectLatest { currentWeatherState ->
                when {
                    currentWeatherState.currentWeatherModel != null -> {
                        val currentModel = currentWeatherState.currentWeatherModel
                        binding.textViewDegree.setText(currentModel.name)
                        binding.textViewMaxMin.setText("Max:${currentModel.main.tempMax}  Min:${currentModel.main.tempMin}")
                    }

                    currentWeatherState.isLoading -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }

                    else -> {

                        Log.d("failure", "failure ${currentWeatherState.errorMsg}")
                    }
                }
            }
        }
    }

    // check for permission
    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        // If we want background location on Android 10.0 and higher, use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // request for permissions
    private fun requestPermissions() {
        permissionRequest.launch(locationPermissions)
    }

    // Permission result
    private val permissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value == true
            }
            permissions.entries.forEach {
                Log.e("LOG_TAG", "${it.key} = ${it.value}")
            }

            if (granted) {
                getLocation()
            } else {
                // your code if permission denied
            }
        }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                // Konum bilgilerini kullanın
                Log.d("Konum", "Enlem: $latitude, Boylam: $longitude")

                homeViewModel.getCurrentWeather(latitude, longitude)
                collectProductState()

            } else {
                // Konum bilgisi alınamadı
                Log.w("Konum", "Konum bilgisi alınamadı")
            }
        }
    }


    // fragmentimiz activity üzerinde kaybolduğunda bindingi null yaparız
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}