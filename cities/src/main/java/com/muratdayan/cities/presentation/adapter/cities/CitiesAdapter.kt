package com.muratdayan.cities.presentation.adapter.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muratdayan.cities.databinding.ItemCityBinding
import com.muratdayan.cities.domain.model.CityModel

class CitiesAdapter : RecyclerView.Adapter<CitiesViewHolder>() {

    private var cityModelList: List<CityModel> = emptyList()

    fun updateData(newCityList: List<CityModel>){
        val diffCallback = CitiesDiffCallback(cityModelList,newCityList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        cityModelList = newCityList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CitiesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cityModelList.size
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(cityModelList[position])
    }


}