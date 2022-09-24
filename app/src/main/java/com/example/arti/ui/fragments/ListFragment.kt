package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.R
import com.example.arti.databinding.ListFragmentBinding
import com.example.arti.ui.adapters.BooksListAdapter
import com.example.arti.ui.viewmodel.BooksApiStatus
import com.example.arti.ui.viewmodel.BooksViewModel
import com.example.arti.ui.viewmodel.BooksViewModelFactory


class ListFragment: Fragment() {
    private lateinit var binding: ListFragmentBinding
    private lateinit var recyclerView: RecyclerView


    private val sharedViewModel: BooksViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BooksViewModelFactory(activity.application))
            .get(BooksViewModel::class.java)
    }
    /*private val sharedViewModel: BooksViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BooksViewModel.Factory(activity.application))
            .get(BooksViewModel::class.java)
        *//*BooksViewModelFactory(
            (activity?.application as BaseApplication).database.booksDao()
        )*//*
    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        //sharedViewModel.getOpenLibrarySearchResponse()
        // showLoadingImage()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = BooksListAdapter { currentBook ->
            sharedViewModel.updateCurrentBook(currentBook) }
        recyclerView.adapter = adapter
        // observe the list of books from the view model and submit it the adapter

        sharedViewModel.books.observe(this.viewLifecycleOwner) { books ->
            books.let {
                adapter.submitList(it)
            }
        }
    }

    private fun showLoadingImage() {
        when(sharedViewModel.status.value) {
            BooksApiStatus.LOADING -> {
                binding.statusImage.visibility = VISIBLE
                binding.statusImage.setImageResource(R.drawable.loading_animation)
            }
            BooksApiStatus.DONE -> {
                binding.statusImage.visibility = GONE
            }
            BooksApiStatus.ERROR -> {
                binding.statusImage.visibility = VISIBLE
                binding.statusImage.setImageResource(R.drawable.ic_connection_error)
            }
            else -> {
                binding.statusImage.visibility = GONE
            }
        }
    }
}


