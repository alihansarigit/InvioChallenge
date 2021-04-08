package com.inviochallenge.viewholder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.R
import com.inviochallenge.adapter.RA_CategoryList
import com.inviochallenge.adapter.RA_CountryList
import com.inviochallenge.model.Category.CategoryList
import com.inviochallenge.model.Country.Country

class VH_BaseAdapter(view:View):RecyclerView.ViewHolder(view) {

    var recyclerView:RecyclerView
    init {
        recyclerView = view.findViewById(R.id.customrec_base_rec)
    }


    fun setData(countryList: Country) {
        var adapter:RA_CountryList = RA_CountryList(itemView.context,countryList)
        var layoutManager:LinearLayoutManager = LinearLayoutManager(itemView.context)
        layoutManager.orientation = RecyclerView.HORIZONTAL

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }

    fun setData(categoryList: CategoryList) {
        var adapter:RA_CategoryList = RA_CategoryList(itemView.context,categoryList)
        var layoutManager:GridLayoutManager = GridLayoutManager(itemView.context,3)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
    }
}