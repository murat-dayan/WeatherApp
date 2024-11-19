package com.muratdayan.cities.presentation.cities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.muratdayan.cities.core.common.Resource
import com.muratdayan.cities.databinding.FragmentCitiesBinding
import com.muratdayan.cities.presentation.adapter.cities.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CitiesViewModel by viewModels()
    private lateinit var citiesAdapter: CitiesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentCitiesBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchCityWithTextWatcher(binding.etSearchCity)

        citiesAdapter = CitiesAdapter()
        binding.rvCities.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = citiesAdapter
        }



        lifecycleScope.launch {
            viewModel.searchedCitiesList.collect{result->
                when(result){
                    is Resource.Error -> {
                        Log.d("CitiesFragment", "Error: ${result.msg}")
                    }
                    is Resource.Idle -> {
                        Log.d("CitiesFragment", "Idle")
                    }
                    is Resource.Loading -> {
                        Log.d("CitiesFragment", "Loading")
                    }
                    is Resource.Success -> {
                        val cityModelResponseList= result.data?.cities
                        cityModelResponseList?.let { list->
                            citiesAdapter.updateData(list)
                        }
                    }
                }
            }
        }
    }

    private fun searchCityWithTextWatcher(editText: EditText){
        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchCity(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                //
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}