package com.inviochallenge.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inviochallenge.model.Product.Product
import com.inviochallenge.model.Product.ProductList
import com.inviochallenge.service.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "ProductViewModel"

class ProductViewModel : ViewModel() {
    private var _productList = MutableLiveData<ProductList>()
    val productList: LiveData<ProductList> get() = _productList

    private var _productDetail = MutableLiveData<Product>()
    val productDetail: LiveData<Product> get() = _productDetail

    fun getProductListFromCountry(countryID: String) {
        viewModelScope.launch {
            try {
                _productList.value =
                    RetrofitInstance.retrofitInstance.getProductListFromCountry(countryID)
            } catch (e: Exception) {
                Log.e(TAG, "getData: " + e.localizedMessage)
            }
        }
    }

    fun getProductListFromCategory(categoryID: String) {
        viewModelScope.launch {
            try {
                _productList.value =
                    RetrofitInstance.retrofitInstance.getProductListFromCategory(categoryID)
            } catch (e: Exception) {
                Log.e(TAG, "getData: " + e.localizedMessage)
            }
        }
    }

    fun getProductDetail(idMeal: String) {
        viewModelScope.launch {
            try {
                _productDetail.value = RetrofitInstance.retrofitInstance.getProductDetail(idMeal)
            } catch (e: Exception) {
                Log.e(TAG, "getProductDetail: " + e.localizedMessage)
            }
        }
    }
}