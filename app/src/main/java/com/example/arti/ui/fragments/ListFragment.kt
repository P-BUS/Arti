package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.arti.R
import com.example.arti.databinding.ListFragmentBinding
import com.example.arti.ui.adapters.BooksListAdapter
import com.example.arti.ui.viewmodel.BooksApiStatus
import com.example.arti.ui.viewmodel.OrderViewModel


class ListFragment: Fragment() {

    // Binding object instance with access to the views in the .xml layout
    private lateinit var binding: ListFragmentBinding

    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Create a ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        showLoadingImage()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        // this is for grid layout
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        // pass to adapter transition on detailed fragment function
        recyclerView.adapter = BooksListAdapter { currentBook ->
                sharedViewModel.updateCurrentBook(currentBook)
                }
    }

    private fun showLoadingImage() {
        when(sharedViewModel.status.value) {
            BooksApiStatus.LOADING -> {
                binding.statusImage.visibility = View.VISIBLE
                binding.statusImage.setImageResource(R.drawable.loading_animation)
            }
            BooksApiStatus.DONE -> {
                binding.statusImage.visibility = View.GONE
            }
            BooksApiStatus.ERROR -> {
                binding.statusImage.visibility = View.VISIBLE
                binding.statusImage.setImageResource(R.drawable.ic_connection_error)
            }
            else -> {
                binding.statusImage.visibility = View.GONE
            }
        }
    }
}


