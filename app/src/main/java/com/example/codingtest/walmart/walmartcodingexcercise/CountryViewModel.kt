package com.example.codingtest.walmart.walmartcodingexcercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryViewModel : ViewModel() {

    private val _countryData = MutableLiveData<List<Country>>()
    val countryData: LiveData<List<Country>> get() = _countryData

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val baseUrl = "http://192.168.86.21"
                val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build()

                val apiService = retrofit.create(ApiService::class.java)
                val countries = apiService.getCountry()

                // Switch to the main thread to update LiveData
                launch(Dispatchers.Main) {
                    _countryData.value = countries
                }
            } catch (e: Exception) {
                // Handle the exception
                launch(Dispatchers.Main) {
                    _countryData.value = ArrayList()
                }
            }
        }
    }
}