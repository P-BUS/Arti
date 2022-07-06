package com.example.arti.ui.bookList

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.R
import com.example.arti.data.Datasource

class ListViewModel : ViewModel() {


    // Initialize data.
    val myDataset = Datasource().loadBooks()

    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    // this is for grid layout
    val gridLayoutManager = GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
    recyclerView.adapter = ItemAdapter(this, myDataset)
    recyclerView.layoutManager = gridLayoutManager

    // Use this setting to improve performance if you know that changes
    // in content do not change the layout size of the RecyclerView
    recyclerView.setHasFixedSize(true)

}