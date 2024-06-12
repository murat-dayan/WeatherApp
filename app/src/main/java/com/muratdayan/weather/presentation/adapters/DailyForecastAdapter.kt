package com.muratdayan.weather.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.ForecastRowBinding
import com.muratdayan.weather.domain.models.DailyModel

class DailyForecastAdapter(
    private val dailyModel: DailyModel
) : RecyclerView.Adapter<DailyForecastAdapter.ForecastRowHolder>() {


    inner class ForecastRowHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ForecastRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastRowHolder {
        val design = LayoutInflater.from(parent.context).inflate(R.layout.forecast_row, parent, false)
        return ForecastRowHolder(design)
    }

    override fun getItemCount(): Int {
        return dailyModel.time.size
    }

    override fun onBindViewHolder(holder: ForecastRowHolder, position: Int) {
        val time = dailyModel.time[position]
        val temperature = dailyModel.temperatures[position]

        holder.binding.forecastRowInfoCard.infoCardTxtTopInfo.text = time
        holder.binding.forecastRowInfoCard.infoCardTxtBottomInfo.text = temperature.toString()
    }

}