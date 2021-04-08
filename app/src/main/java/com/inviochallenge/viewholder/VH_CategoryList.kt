package com.inviochallenge.viewholder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inviochallenge.MainActivity
import com.inviochallenge.ProductListActivity
import com.inviochallenge.R
import com.inviochallenge.model.Category.Category
import com.inviochallenge.model.Country.Meal
import com.inviochallenge.utils.Id

class VH_CategoryList(view: View):RecyclerView.ViewHolder(view) {

    var txt_CategoryName:TextView
    var img_ProductImage: ImageView

    init {
        txt_CategoryName = view.findViewById(R.id.customrec_category_txtProductName)
        img_ProductImage = view.findViewById(R.id.customrec_category_imgProductImage)
    }

    fun setData(item: Category?) {
        if(item?.strCategory!!.length>8){
            txt_CategoryName.text = item?.strCategory.substring(0,5)+"..."
        }else{
            txt_CategoryName.text = item?.strCategory
        }

        Glide.with(itemView.context).load(item?.strCategoryThumb).into(img_ProductImage)

        itemView.setOnClickListener {
            var intent:Intent = Intent(itemView.context,ProductListActivity::class.java)
            intent.putExtra(Id.category_id.toString(),item?.strCategory)
            itemView.context.startActivity(intent)
        }
    }
}