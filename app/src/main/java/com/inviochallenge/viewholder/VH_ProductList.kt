package com.inviochallenge.viewholder

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.inviochallenge.ProductDetailActivity
import com.inviochallenge.R
import com.inviochallenge.database.FavouriteDatabase
import com.inviochallenge.model.Product.Meal
import com.inviochallenge.utils.Id
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VH_ProductList(view: View) : RecyclerView.ViewHolder(view) {

    var txt_ProductName: TextView
    var img_ProductImage: ImageView
    var img_ProductFavourite: ImageView

    init {
        txt_ProductName = view.findViewById(R.id.customrec_product_txtProductName)
        img_ProductImage = view.findViewById(R.id.customrec_product_imgProductImage)
        img_ProductFavourite = view.findViewById(R.id.customrec_product_imgProductStar)
    }

    fun setData(item: Meal) {

        if (item?.strMeal!!.length > 12) {
            txt_ProductName.text = item?.strMeal.substring(0, 9) + "..."
        } else {
            txt_ProductName.text = item?.strMeal
        }

        Glide.with(itemView.context).load(item.strMealThumb).into(img_ProductImage)

        CoroutineScope(Dispatchers.IO).launch {
//            val dao = FavouriteDatabase(itemView.context!!).favouriteDao()
            try{
            var dao = Room.databaseBuilder(
                itemView.context!!,
                FavouriteDatabase::class.java, "FavouriteDatabase"
            ).build().favouriteDao()

                var items:Meal? = dao.getFavouriteFoodById(item.idMeal)

                if(items==null){
                    img_ProductFavourite.setBackgroundResource(R.drawable.ic_star_empty)
                }else {
                    img_ProductFavourite.setBackgroundResource(R.drawable.ic_star_full)
                }
            }
            catch (ex: Exception){
                Log.e("error", "error: " + ex.localizedMessage)
            }
        }



        img_ProductFavourite.setOnClickListener {
            try{
                CoroutineScope(Dispatchers.IO).launch {
                    var dao = Room.databaseBuilder(
                        itemView.context!!,
                        FavouriteDatabase::class.java, "FavouriteDatabase"
                    ).build().favouriteDao()

                    var items:Meal? = dao.getFavouriteFoodById(item.idMeal)
                    if(items==null){
                        dao.insertAll(item)
                        img_ProductFavourite.setBackgroundResource(R.drawable.ic_star_full)

                    }else{
                        dao.delete(item)
                        img_ProductFavourite.setBackgroundResource(R.drawable.ic_star_empty)
                    }

//                    var s = dao.getFavouriteFoodById(item.idMeal)
//                    Log.e("TAG", "setData: $s")
                }
            }catch (ex: Exception){
                Log.e("TAG", "setData: " + ex.localizedMessage)
            }
        }

        itemView.setOnClickListener {
            var intent: Intent = Intent(itemView.context, ProductDetailActivity::class.java)
            intent.putExtra(Id.meal_id.toString(), item?.idMeal)
            itemView.context!!.startActivity(intent)
        }
    }
}