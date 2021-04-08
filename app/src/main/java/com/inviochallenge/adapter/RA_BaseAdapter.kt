package com.inviochallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.R
import com.inviochallenge.model.Category.CategoryList
import com.inviochallenge.model.Country.Country
import com.inviochallenge.viewholder.VH_BaseAdapter

class RA_BaseAdapter(val context: Context, var countryList: Country, var categoryList:CategoryList):RecyclerView.Adapter<VH_BaseAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH_BaseAdapter {
        var view: View = LayoutInflater.from(context).inflate(R.layout.custom_rec_base,parent,false)
        return VH_BaseAdapter(view)
    }

    override fun onBindViewHolder(holder: VH_BaseAdapter, position: Int) {
        when(position){
            0-> {
                holder.setData(countryList)
            }

            1->{
                holder.setData(categoryList)
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}