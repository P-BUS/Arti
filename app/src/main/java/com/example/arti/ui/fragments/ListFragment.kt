package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arti.R
import com.example.arti.data.model.Datasource
import com.example.arti.databinding.ListFragmentBinding
import com.example.arti.ui.adapters.ListAdapter
import com.example.arti.ui.viewmodel.OrderViewModel


class ListFragment: Fragment() {

    // Binding object instance with access to the views in the .xml layout
    private lateinit var binding: ListFragmentBinding

    private val sharedViewModel: OrderViewModel by activityViewModels()

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
        val myDataset = Datasource.loadBooks()

        val recyclerView = binding.recyclerView
        // this is for grid layout
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        // pass to adapter transition on detailed fragment function
        recyclerView.adapter = ListAdapter(
            context!!,
            myDataset, {
            goToDetailsScreen() }, {
            sharedViewModel.updateCurrentBook(it) }, {
            sharedViewModel.updateCurrentPrice(sharedViewModel.currentBook.value?.bookPrice ?: 0)
            }
        )

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }

    fun goToDetailsScreen() {
        findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
    }
}


