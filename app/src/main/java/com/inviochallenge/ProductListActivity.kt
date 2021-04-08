package com.inviochallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.adapter.RA_ProductList
import com.inviochallenge.utils.Id
import com.inviochallenge.viewmodels.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: ProductViewModel
    private lateinit var recyclerView: RecyclerView
    lateinit private var txt_Title: TextView
    lateinit private var img_BackButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setID()
        setListener()

        img_BackButton.visibility = View.VISIBLE

        var categoryID: String? = intent.getStringExtra(Id.category_id.toString())?.let{
            txt_Title.text = it
            it
        }

        var countryID: String? = intent.getStringExtra(Id.country_id.toString())?.let{
            txt_Title.text = it
            it
        }


        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        viewModel.productList.observe(this, { productList ->

            var adapter:RA_ProductList = RA_ProductList(this,productList)
            var layoutManager:GridLayoutManager = GridLayoutManager(this,2)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        })


        CoroutineScope(Dispatchers.IO).launch {
            if (categoryID != null) {
                viewModel.getProductListFromCategory(categoryID)
            } else {
                viewModel.getProductListFromCountry(countryID!!)
            }


        }
    }

    fun setID(){
        recyclerView = findViewById(R.id.actProductList_recyclerView)
        img_BackButton = findViewById(R.id.customtoolbar_imgBackButton)
        txt_Title = findViewById(R.id.customtoolbar_txtTitle)
    }
    fun setListener(){
        img_BackButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            img_BackButton.id->{
                onBackPressed()
            }
        }
    }
}