package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arti.databinding.DetailsFragmentBinding
import com.example.arti.other.ImageLoader
import com.example.arti.other.ImageSize
import com.example.arti.ui.viewmodel.BooksViewModel

class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding
    private val sharedViewModel: BooksViewModel by activityViewModels()
    /*private val sharedViewModel: BooksViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, BooksViewModelFactory(activity.application))
            .get(BooksViewModel::class.java)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindBook()
    }

    private fun bindBook() {
        binding.bookAuthorName.text = sharedViewModel.currentBook.value?.author_name?.get(0).toString()
        binding.bookDetailName.text = sharedViewModel.currentBook.value?.title.toString()
        sharedViewModel.currentBook.value?.let {
            //Load the image from web service using Coil
            ImageLoader().loadImage(binding.bookDetailImage, it.cover_i, ImageSize.L) }
    }
}


