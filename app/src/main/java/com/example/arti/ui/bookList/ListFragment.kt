package com.example.arti.ui.bookList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arti.data.Datasource
import com.example.arti.databinding.ListFragmentBinding


class ListFragment: Fragment() {

    // Binding object instance with access to the views in the .xml layout
    private lateinit var binding: ListFragmentBinding

    private val viewModel: ListViewModel by viewModels()

    // Create a ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = ListFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize data.
        val myDataset = Datasource().loadBooks()

        val recyclerView = binding.recyclerView
        // this is for grid layout
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = ListAdapter(context!!, myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}


