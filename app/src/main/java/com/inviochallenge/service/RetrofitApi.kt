package com.inviochallenge.service

import com.inviochallenge.model.Category.CategoryList
import com.inviochallenge.model.Country.Country
import com.inviochallenge.model.Product.Product
import com.inviochallenge.model.Product.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("list.php?a=list")
    suspend fun getCountry():Country

    @GET("categories.php")
    suspend fun getCategory():CategoryList

    @GET("filter.php")
    suspend fun getProductListFromCountry(@Query("a") countryName:String): ProductList

    @GET("filter.php")
    suspend fun getProductListFromCategory(@Query("c") categoryId:String): ProductList

    @GET("lookup.php")
    suspend fun getProductDetail(@Query("i") idMeal:String):Product

}