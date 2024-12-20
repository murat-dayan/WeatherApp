package com.muratdayan.cities.presentation.adapter.cities

import androidx.recyclerview.widget.RecyclerView
import com.muratdayan.cities.databinding.ItemCityBinding
import com.muratdayan.cities.domain.model.CityModel

class CitiesViewHolder(
    val binding: ItemCityBinding,
    val onAddCityClicked: (CityModel) -> Unit
): RecyclerView.ViewHolder(binding.root){
    fun bind(city: CityModel){
        binding.tvItemCity.text = city.name
        binding.ibtnAddCity.setOnClickListener {
            onAddCityClicked(city)
        }
    }
}