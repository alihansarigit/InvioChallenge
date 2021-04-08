package com.inviochallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.R
import com.inviochallenge.model.Country.Country
import com.inviochallenge.viewholder.VH_CountryList

class RA_CountryList(val context:Context,var items:Country): RecyclerView.Adapter<VH_CountryList>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH_CountryList {
        var view: View = LayoutInflater.from(context).inflate(R.layout.custom_rec_country,parent,false)
        return VH_CountryList(view)
    }

    override fun onBindViewHolder(holder: VH_CountryList, position: Int) {
        holder.setData(items.meals.get(position))
    }

    override fun getItemCount(): Int {
        return items.meals.size
    }
}