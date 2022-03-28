package com.eb.weatherapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.weatherapp.models.CurrentData
import com.eb.weatherapp.ui.viewholders.HourlyViewHolder

class HourlyAdapter(
    private var hourlyItems: List<CurrentData>,
) : RecyclerView.Adapter<HourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        return HourlyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.bind(hourlyItems[position])
    }

    override fun getItemCount(): Int {
        return hourlyItems.size
    }
}
