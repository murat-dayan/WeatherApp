package com.muratdayan.weather.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEach
import androidx.navigation.fragment.findNavController
import com.muratdayan.weather.MainActivity
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    // binding usage in fragment
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        (activity as? MainActivity)?.binding?.bottomNavigationView?.visibility = View.GONE

        binding.btnGetStarted.setOnClickListener {

            findNavController().navigate(R.id.navigate_splashFragment_to_homeFragment)

            (activity as? MainActivity)?.binding?.bottomNavigationView?.visibility = View.VISIBLE

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}