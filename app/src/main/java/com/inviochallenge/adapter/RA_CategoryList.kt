package com.inviochallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.R
import com.inviochallenge.model.Category.CategoryList
import com.inviochallenge.model.Country.Country
import com.inviochallenge.viewholder.VH_CategoryList
import com.inviochallenge.viewholder.VH_CountryList

class RA_CategoryList(val context:Context, var items:CategoryList): RecyclerView.Adapter<VH_CategoryList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH_CategoryList {
        var view: View = LayoutInflater.from(context).inflate(R.layout.custom_rec_category,parent,false)
        return VH_CategoryList(view)
    }

    override fun onBindViewHolder(holder: VH_CategoryList, position: Int) {
        holder.setData(items.categories.get(position))
    }

    override fun getItemCount(): Int {
        return items.categories.size
    }
}