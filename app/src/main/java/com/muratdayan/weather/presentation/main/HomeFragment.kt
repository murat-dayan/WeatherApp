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

    private var locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        if (checkPermissions()) {
            getLocation()
        } else {
            requestPermissions()
        }




        return binding.root
    }

    fun getTimeFromDateTime(dateTime: String): String {
        // Verilen stringin saat kısmını çıkarmak için substring kullanıyoruz
        return dateTime.substring(11, 16)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthAndDayFromTimestamp(timestamp: Long): String {
        // Unix zaman damgasını LocalDateTime nesnesine çeviriyoruz
        val dateTime = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(timestamp),
            java.time.ZoneId.systemDefault()
        )

        // Ay ismini ve günü alıyoruz (Örneğin: "July, 12")
        val month = dateTime.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val day = dateTime.get(ChronoField.DAY_OF_MONTH)

        // İngilizce olarak formatlıyoruz
        return "$month, $day"
    }

    // collect the state of the product from the homeViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
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

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude

                // location info is received
                Log.d("Konum", "Enlem: $latitude, Boylam: $longitude")

                homeViewModel.getCurrentAndHourlyForecast(lat = latitude, lon = longitude)
                collectProductState()

            } else {
                // location info is not received
                Log.w("Konum", "Konum bilgisi alınamadı")
            }
        }
    }


    // if the fragment is destroyed, the binding object is set to null
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}