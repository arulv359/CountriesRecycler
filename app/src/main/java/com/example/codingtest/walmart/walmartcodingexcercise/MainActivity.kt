package com.example.codingtest.walmart.walmartcodingexcercise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest.walmart.myapplication4.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseUrl = "https://gist.githubusercontent.com"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create the ApiService using Retrofit
        val apiService = retrofit.create(ApiService::class.java)

        // Use Coroutines to make the network call
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val post = apiService.getCountry()
                // Update UI on the main thread
                launch(Dispatchers.Main) {
                    adapter = CountryAdapter(post)
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                // Handle the exception
                launch(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}