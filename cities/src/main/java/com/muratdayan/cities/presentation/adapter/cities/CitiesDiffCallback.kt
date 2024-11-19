package com.muratdayan.cities.presentation.adapter.cities

import androidx.recyclerview.widget.DiffUtil
import com.muratdayan.cities.domain.model.CityModel

class CitiesDiffCallback(
    private val oldList: List<CityModel>,
    private val newList: List<CityModel>
) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}