package com.muratdayan.weather.presentation.weather_detail

import android.annotation.SuppressLint
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
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {

    // binding usage in fragment
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    private val weatherDetailViewModel: WeatherDetailViewModel by viewModels()


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)

        binding.detailCardUvIndex.textViewSunrise.text = "UV INDEX"
        binding.detailCardUvIndex.textViewSunsetTime.text = "Moderate"

        binding.rvForecasts.setHasFixedSize(true)
        binding.rvForecasts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        weatherDetailViewModel.getDailyForecastWeather(52.52, 13.41)
        collectProductState()

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun unixTimestampToTimeString(timestamp: Long): String {
        val instant = Instant.ofEpochSecond(timestamp)
        val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }


    @SuppressLint("SetTextI18n")
    private fun collectProductState() {
        lifecycleScope.launch {
            weatherDetailViewModel.dailyModelState.collectLatest { dailyModelState ->
                when {
                    dailyModelState.dailymodel != null -> {
                        val dailyModel = dailyModelState.dailymodel
                        println(dailyModel.time[0])
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}