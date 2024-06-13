package com.muratdayan.weather.presentation.weather_detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muratdayan.weather.databinding.FragmentWeatherDetailBinding
import com.muratdayan.weather.presentation.adapters.DailyForecastAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {

    // binding usage in fragment
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    private val weatherDetailViewModel: WeatherDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)

        binding.rvForecasts.setHasFixedSize(true)
        binding.rvForecasts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        weatherDetailViewModel.getDailyForecastWeather(52.52, 13.41)
        collectProductState()

        return binding.root
    }



    private fun collectProductState() {
        lifecycleScope.launch {
            weatherDetailViewModel.dailyModelState.collectLatest { dailyModelState ->
                when {
                    dailyModelState.dailymodel != null -> {
                        val dailyModel = dailyModelState.dailymodel
                        println(dailyModel.time[0])
                        binding.rvForecasts.adapter = DailyForecastAdapter(dailyModel)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}