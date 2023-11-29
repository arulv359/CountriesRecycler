package com.example.codingtest.walmart.walmartcodingexcercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CountryListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        //Instantiating ViewModel
        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.countryData.observe(viewLifecycleOwner) {
            recyclerView.adapter = CountryAdapter(it)
        }

        return view
    }
}