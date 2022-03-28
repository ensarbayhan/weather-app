package com.eb.weatherapp.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.weatherapp.databinding.ItemDailyBinding
import com.eb.weatherapp.models.Data
import com.eb.weatherapp.utils.toDayString
import com.eb.weatherapp.utils.toPercentage
import com.eb.weatherapp.utils.withDegree


class DailyViewHolder(private var binding: ItemDailyBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): DailyViewHolder {
            return DailyViewHolder(ItemDailyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    fun bind(item: Data) {
        binding.day.text = item.time?.toDayString()
        binding.humidity.text = item.humidity?.toPercentage()
        binding.maxDegree.text = item.temperatureMax?.withDegree()
        binding.minDegree.text = item.temperatureMin?.withDegree()

        item.icon?.let {
            binding.icon.setImageResource(it.icon)
        }
    }
}
