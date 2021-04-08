package com.inviochallenge.service

import com.inviochallenge.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance {

    companion object{
        private val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        val retrofitInstance: RetrofitApi by lazy{
            retrofit.create(RetrofitApi::class.java)
        }
    }
}