package com.muratdayan.weather.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    // fragmentlarda binding kullanımı
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // binding bağlanması
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.textView.setText("home fragment")

        return binding.root
    }

    // fragmentimiz activity üzerinde kaybolduğunda bindingi null yaparız
    override fun onDestroyView() {
        super.onDestroyView()
        TODO()
        _binding = null
    }


}