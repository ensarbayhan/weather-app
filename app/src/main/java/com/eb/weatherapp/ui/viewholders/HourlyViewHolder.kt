package com.eb.weatherapp.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.weatherapp.databinding.ItemHourlyBinding
import com.eb.weatherapp.models.CurrentData
import com.eb.weatherapp.utils.toHourString
import com.eb.weatherapp.utils.toPercentage
import com.eb.weatherapp.utils.withDegree


class HourlyViewHolder(private var binding: ItemHourlyBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): HourlyViewHolder {
            return HourlyViewHolder(ItemHourlyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun bind(item: CurrentData) {
        binding.humidity.text = item.humidity?.toPercentage()
        binding.degree.text = item.apparentTemperature?.withDegree()
        binding.hour.text = item.time?.toHourString()
        item.icon?.let {
            binding.icon.setImageResource(it.icon)
        }
    }
}
