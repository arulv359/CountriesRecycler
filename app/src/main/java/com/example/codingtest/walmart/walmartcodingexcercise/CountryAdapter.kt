package com.example.codingtest.walmart.walmartcodingexcercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(private val itemList: List<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    private lateinit var context: Context

    // Creating view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_item_layout, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    //Populating the actual values
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aCountry = itemList[position]
        holder.countryName.text = aCountry.name
        holder.region.text = context.getString(R.string.region_delimiters, aCountry.region)
        holder.countryCode.text = aCountry.code
        holder.capitalName.text = aCountry.capital
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