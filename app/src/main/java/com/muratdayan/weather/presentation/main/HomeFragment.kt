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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.muratdayan.common.utils.Resource
import com.muratdayan.weather.databinding.FragmentHomeBinding
import com.muratdayan.weather.domain.models.DailyModel
import com.muratdayan.weather.presentation.adapters.DailyForecastAdapter
import com.muratdayan.weather.presentation.weather_detail.WeatherDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.util.Locale
import kotlin.collections.get
import kotlin.toString

// handle the UI and business logic of the HomeFragment
@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    // binding usage in fragment
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private val weatherDetailViewModel: WeatherDetailViewModel by viewModels()

    private lateinit var dailyForecastAdapter: DailyForecastAdapter


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

        binding.detailCardUvIndex.textViewSunrise.text = "UV INDEX"
        binding.detailCardUvIndex.textViewSunsetTime.text = "Moderate"

        binding.rvForecasts.setHasFixedSize(true)
        binding.rvForecasts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvForecasts.isNestedScrollingEnabled = false

        val dailyModel = DailyModel(
            temperatures = listOf(10.2, 12.5, 15.8, 18.1, 20.4, 22.7, 25.0),
            uv_index_max = listOf(1.2, 2.5, 3.8, 4.1,3.4, 2.7, 1.0)
        )

        dailyForecastAdapter = DailyForecastAdapter(dailyModel)
        binding.rvForecasts.adapter = dailyForecastAdapter

        weatherDetailViewModel.getDailyForecastWeather(52.52, 13.41)
        collectProductStateDetail()

        arguments?.let {
            /*forecastModel = WeatherDetailFragmentArgs.fromBundle(it).forecastModel

            forecastModel?.let {
                binding.txtViewLocalArea.text = it.city.name
                binding.detailCardSunrise.textViewSunriseTime.text =
                    unixTimestampToTimeString(it.city.sunrise)
                binding.detailCardSunrise.textViewSunsetTime.text =
                    "Sunset: ${unixTimestampToTimeString(it.city.sunset)}"
            }*/
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkAndRequestPermissions()
        collectProductState()
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun unixTimestampToTimeString(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }


    @SuppressLint("SetTextI18n")
    private fun collectProductStateDetail() {
        lifecycleScope.launch {
            weatherDetailViewModel.dailyModelState.collectLatest { dailyModelState ->
                when {
                    dailyModelState.dailymodel != null -> {
                        val dailyModel = dailyModelState.dailymodel
                        //println(dailyModel.time[0])
                        /*forecastModel?.let {
                            binding.rvForecasts.adapter = DailyForecastAdapter(
                                dailyModel,
                                forecastModel!!
                            )
                        }*/

                        binding.detailCardUvIndex.textViewSunriseTime.text = dailyModel.uv_index_max[0].toString()

                    }

                    dailyModelState.isLoading -> {
                        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Log.d("failure", "failure ${dailyModelState.error}")
                    }
                }
            }
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