package com.eb.weatherapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eb.weatherapp.models.Data
import com.eb.weatherapp.ui.viewholders.DailyViewHolder

class DailyAdapter(
    private var dailyItems: List<Data>,
) : RecyclerView.Adapter<DailyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(dailyItems[position])
    }

    override fun getItemCount(): Int {
        return dailyItems.size
    }
}
