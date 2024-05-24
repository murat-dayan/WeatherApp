package com.muratdayan.weather.presentation.weather_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentHomeBinding
import com.muratdayan.weather.databinding.FragmentWeatherDetailBinding


class WeatherDetailFragment : Fragment() {

    // fragmentlarda binding kullanımı
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}