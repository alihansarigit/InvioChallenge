package com.inviochallenge.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.inviochallenge.model.Category.CategoryList
import com.inviochallenge.model.Country.Country
import com.inviochallenge.service.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    private var _countryList = MutableLiveData<Country>()
    private var _categoryList = MutableLiveData<CategoryList>()
    val countryList: LiveData<Country> get() = _countryList
    val categoryList: LiveData<CategoryList> get() = _categoryList

    fun getCountry2() {
        viewModelScope.launch {
            try {
                _countryList.value = RetrofitInstance.retrofitInstance.getCountry()
            } catch (e: Exception) {
                _countryList.value = RetrofitInstance.retrofitInstance.getCountry()
                Log.e(TAG, "getData: " + e.localizedMessage)
            }
        }
    }
    fun getCategory2() {
        viewModelScope.launch {
            try {
                _categoryList.value = RetrofitInstance.retrofitInstance.getCategory()
            } catch (e: Exception) {
                _categoryList.value = RetrofitInstance.retrofitInstance.getCategory()
                Log.e(TAG, "getData: " + e.localizedMessage)
            }
        }
    }
//
//        fun getPopular() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = RetrofitInstance.retrofitInstance.getPopularTV(API_KEY,"tr-TR",null)))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

//    fun getCountry(): LiveData<Resource<Country>>{
//        return liveData(Dispatchers.IO) {
//            emit(Resource.loading(data = null))
//            try {
//                emit(Resource.success(data = RetrofitInstance.retrofitInstance.getCountry()))
//            } catch (exception: Exception) {
//                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//            }
//        }
//    }
//    fun getCategory(): LiveData<Resource<CategoryList>>{
//        return liveData(Dispatchers.IO) {
//            emit(Resource.loading(data = null))
//            try {
//                emit(Resource.success(data = RetrofitInstance.retrofitInstance.getCategory()))
//            } catch (exception: Exception) {
//                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//            }
//        }
//    }



}