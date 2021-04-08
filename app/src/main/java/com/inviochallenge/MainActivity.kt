package com.inviochallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inviochallenge.adapter.RA_BaseAdapter
import com.inviochallenge.adapter.RA_CountryList
import com.inviochallenge.model.Country.Country
import com.inviochallenge.viewmodels.HomeViewModel
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

private lateinit var viewModel: HomeViewModel

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivityTAG"
    lateinit private var progressBar: ProgressBar
    lateinit private var toolbar: Toolbar
    lateinit private var recyclerView: RecyclerView
    lateinit private var txt_title:TextView
    var country: Country? = null

    var i: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setID()

        txt_title.text = "HomePage"


        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.countryList.observe(this, { countryList ->
            //  Log.e(TAG, "onCreate: "+ countryList)
            Log.e(TAG, "country")
            country = countryList
        })


        viewModel.categoryList.observe(this, { categoryList ->
            //   Log.e(TAG, "onCreate: "+ categoryList)
            Log.e(TAG, "category")
            progressBar.visibility = View.GONE


            var adapter: RA_BaseAdapter = RA_BaseAdapter(this@MainActivity, country!!, categoryList)
            var layoutManager:LinearLayoutManager = LinearLayoutManager(this)
            layoutManager.orientation = RecyclerView.VERTICAL
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)

            recyclerView.adapter = adapter
        })

        progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {

            val time = measureTimeMillis {
                val responseCountry = async { viewModel.getCountry2() }.await()
                val responseCategory = async { viewModel.getCategory2() }.await()
            }

            Log.e(TAG, "Geçen süre: $time")
        }
    }

    private fun setID(){
        toolbar = findViewById(R.id.customtoolbar_toolbar)
        progressBar = findViewById(R.id.actMain_progressBar)
        recyclerView = findViewById(R.id.actMain_recyclerView)
        txt_title = findViewById(R.id.customtoolbar_txtTitle)
    }

}