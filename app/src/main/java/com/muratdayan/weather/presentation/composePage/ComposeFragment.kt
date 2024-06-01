package com.muratdayan.weather.presentation.composePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.motion.widget.Debug.getLocation
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.FragmentComposeBinding
import com.muratdayan.weather.databinding.FragmentHomeBinding


class ComposeFragment : Fragment() {

    private var _binding: FragmentComposeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComposeBinding.inflate(inflater, container, false)


        binding.composeView.setContent { 
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                
                Text(text = "Welcome Compose Fragment")
                Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = null )
            }
        }


        return binding.root
    }

    
}