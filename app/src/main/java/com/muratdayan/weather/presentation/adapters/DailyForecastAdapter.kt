package com.muratdayan.weather.presentation.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.muratdayan.weather.R
import com.muratdayan.weather.databinding.ForecastRowBinding
import com.muratdayan.weather.domain.models.DailyModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ForecastRowHolder, position: Int) {
        val time = dailyModel.time[position]
        val temperature = dailyModel.temperatures[position]

        val day = getDayNameAbbreviation(time)

        holder.binding.forecastRowInfoCard.infoCardTxtTopInfo.text = temperature.toString()
        holder.binding.forecastRowInfoCard.infoCardTxtBottomInfo.text = day
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDayNameAbbreviation(dateString: String): String {
        // Tarih formatını belirliyoruz
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        // String'i LocalDate nesnesine dönüştürüyoruz
        val localDate = LocalDate.parse(dateString, formatter)
        // Gün ismini kısaltma olarak alıyoruz
        return localDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
    }

}