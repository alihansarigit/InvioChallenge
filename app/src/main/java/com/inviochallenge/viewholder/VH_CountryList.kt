package com.inviochallenge.viewholder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.ProductListActivity
import com.inviochallenge.R
import com.inviochallenge.model.Country.Meal
import com.inviochallenge.utils.Id

class VH_CountryList(view: View):RecyclerView.ViewHolder(view) {

    var txt_CountryName:TextView

    init {
        txt_CountryName = view.findViewById(R.id.customrec_country_txtProductName)
    }

    fun setData(item: Meal) {
        txt_CountryName.text = item.strArea

        itemView.setOnClickListener {
            var intent: Intent = Intent(itemView.context, ProductListActivity::class.java)
            intent.putExtra(Id.country_id.toString(),item?.strArea)
            itemView.context.startActivity(intent)
        }
    }
}