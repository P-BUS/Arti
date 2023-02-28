package com.example.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.arti.R
import com.example.arti.databinding.DetailsFragmentBinding
import com.example.arti.ui.viewmodel.BooksViewModel
import com.example.data.model.Book
import com.example.data.utils.ImageLoader
import com.example.data.utils.ImageSize
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    private val sharedViewModel: BooksViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            sharedViewModel.currentBook
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect { currentBook ->
                    bindBook(currentBook)
                }
        }

        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.GONE
    }

    private fun bindBook(currentBook: Book) {
        binding.bookAuthorName.text = currentBook.authorName[0]
        binding.bookDetailName.text = currentBook.title
        currentBook.let {
            //Load the image from web service using Coil
            ImageLoader.loadImage(binding.bookDetailImage, it.coverI, ImageSize.L)
        }
    }
}


