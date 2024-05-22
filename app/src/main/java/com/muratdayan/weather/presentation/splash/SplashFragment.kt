package com.muratdayan.weather.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    // fragmentlarda binding kullanımı
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(R.id.navigate_splashFragment_to_homeFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}