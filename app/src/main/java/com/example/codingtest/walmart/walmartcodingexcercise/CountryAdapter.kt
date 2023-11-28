package com.example.codingtest.walmart.walmartcodingexcercise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codingtest.walmart.myapplication4.R

class CountryAdapter(private val itemList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    // Creating view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_layout, parent, false)
        return ViewHolder(view)
    }

    //Populating the actual values
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.countryName.text = "${item.name}, ${item.region}"
        holder.countryCode.text = item.code
        holder.capitalName.text = item.capital
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    //View Holder
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.countryName)
        val region: TextView = itemView.findViewById(R.id.region)
        val countryCode: TextView = itemView.findViewById(R.id.code)
        val capitalName: TextView = itemView.findViewById(R.id.capital)
    }
}