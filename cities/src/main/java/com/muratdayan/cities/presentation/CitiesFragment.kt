package com.muratdayan.cities.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muratdayan.cities.R
import com.muratdayan.cities.core.common.Resource
import com.muratdayan.cities.databinding.FragmentCitiesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CitiesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentCitiesBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchCity("Adana")

        lifecycleScope.launch {
            viewModel.searchedCitiesList.collect{result->
                when(result){
                    is Resource.Error -> TODO()
                    is Resource.Idle -> TODO()
                    is Resource.Loading -> TODO()
                    is Resource.Success -> {
                        Log.d("CitiesFragment", "Success: ${result.data?.cities?.get(0)?.name}")
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