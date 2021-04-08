package com.inviochallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.R
import com.inviochallenge.model.Product.ProductList
import com.inviochallenge.viewholder.VH_ProductList

class RA_ProductList(val context:Context, var items: ProductList): RecyclerView.Adapter<VH_ProductList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH_ProductList {
        var view: View = LayoutInflater.from(context).inflate(R.layout.custom_rec_product,parent,false)
        return VH_ProductList(view)
    }

    override fun onBindViewHolder(holder: VH_ProductList, position: Int) {
        holder.setData(items.meals.get(position))
    }

    override fun getItemCount(): Int {
        return items.meals.size
    }
}