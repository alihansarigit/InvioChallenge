package com.inviochallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import com.inviochallenge.database.FavouriteDatabase
import com.inviochallenge.model.Product.Meal
import com.inviochallenge.model.Product.Product
import com.inviochallenge.utils.Id
import com.inviochallenge.viewmodels.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit private var txtProductName:TextView
    lateinit private var txtProductCategory:TextView
    lateinit private var txtProductDescription:TextView
    lateinit private var txtTitle:TextView
    lateinit private var imgBackButton:ImageView
    lateinit private var imgFavouriteButton:ImageView
    lateinit private var imgProductImage:ImageView
    lateinit private var viewModel: ProductViewModel
    lateinit private var frmprogress_Frm:FrameLayout

    var meal:Meal? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        var mealID:String? = intent.getStringExtra(Id.meal_id.toString())
        setID()
        setListener()

        imgBackButton.visibility = View.VISIBLE
        imgFavouriteButton.visibility = View.VISIBLE
        txtTitle.text = "Product Detail"

        CoroutineScope(Dispatchers.IO).launch {
            try{
                var dao = Room.databaseBuilder(
                    this@ProductDetailActivity,
                    FavouriteDatabase::class.java, "FavouriteDatabase"
                ).build().favouriteDao()

                var items:Meal? = dao.getFavouriteFoodById(mealID!!)

                if(items==null){
                    imgFavouriteButton.setBackgroundResource(R.drawable.ic_star_empty)
                }else {
                    imgFavouriteButton.setBackgroundResource(R.drawable.ic_star_full)
                }

            }
            catch (ex: Exception){
                Log.e("error", "error: " + ex.localizedMessage)
            }
        }

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        viewModel.productDetail.observe(this,{productDetail->

            meal =  productDetail.meals.get(0)
            meal?.let{
                txtProductName.text = it.strMeal
                txtProductDescription.text = it.strInstructions
                txtProductCategory.text = it.strCategory
                Glide.with(this).load(it.strMealThumb).into(imgProductImage)

                frmprogress_Frm.visibility = View.GONE
            }

        })

        CoroutineScope(Dispatchers.IO).launch {
            mealID?.let {
                viewModel.getProductDetail(mealID)
            }
        }
    }

    fun setID(){
        imgBackButton = findViewById(R.id.customtoolbar_imgBackButton)
        imgProductImage = findViewById(R.id.actProductDetail_imgProductImage)
        imgFavouriteButton = findViewById(R.id.customtoolbar_imgFavourite)
        txtTitle = findViewById(R.id.customtoolbar_txtTitle)
        txtProductName = findViewById(R.id.actProductDetail_txt_ProductName)
        txtProductCategory = findViewById(R.id.actProductDetail_txt_ProductCategory)
        txtProductDescription = findViewById(R.id.actProductDetail_txt_ProductDescriptionText)
        frmprogress_Frm = findViewById(R.id.customprogress_frm)
    }

    fun setListener(){
        imgFavouriteButton.setOnClickListener(this)
        imgBackButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            imgFavouriteButton.id->{
                try{
                    CoroutineScope(Dispatchers.IO).launch {
                        var dao = Room.databaseBuilder(
                            this@ProductDetailActivity,
                            FavouriteDatabase::class.java, "FavouriteDatabase"
                        ).build().favouriteDao()

                        var item: Meal? = dao.getFavouriteFoodById(meal!!.idMeal)
                        if(item==null){
                            dao.insertAll(meal!!)
                            imgFavouriteButton.setBackgroundResource(R.drawable.ic_star_full)

                        }else{
                            dao.delete(item)
                            imgFavouriteButton.setBackgroundResource(R.drawable.ic_star_empty)
                        }
                    }
                }catch (ex: Exception){
                    Log.e("TAG", "setData: " + ex.localizedMessage)
                }
            }
            imgBackButton.id->{
                onBackPressed()
            }
        }
    }
}