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
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    // fragmentlarda binding kullanımı
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var locationRequest: LocationRequest

    private var locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // binding bağlanması
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        if (checkPermissions()){
            getLocation()
        } else {
            requestPermissions()
        }


        return binding.root
    }

    // check for permission
    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        // If we want background location on Android 10.0 and higher, use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // request for permissions
    private fun requestPermissions() {
        permissionRequest.launch(locationPermissions)
    }

    // Permission result
    private val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
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
                Toast.makeText(requireContext(), "Enlem: $latitude, Boylam: $longitude", Toast.LENGTH_SHORT).show()
            } else {
                // Konum bilgisi alınamadı
                Log.w("Konum", "Konum bilgisi alınamadı")
                Toast.makeText(requireContext(), "Konum bilgisi alınamadı", Toast.LENGTH_SHORT).show()
            }
        }
    }



    // fragmentimiz activity üzerinde kaybolduğunda bindingi null yaparız
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}