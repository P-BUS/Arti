package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arti.databinding.DetailsFragmentBinding
import com.example.arti.other.Constants
import com.example.arti.other.ImageLoader
import com.example.arti.other.ImageSize
import com.example.arti.ui.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    val sharedViewModel: BooksViewModel by activityViewModels()

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
        with(binding.detailsFragmentWebView) {
            loadUrl(Constants.WEB_URL)
            webViewClient = WebViewClient()
        }
    }

    private fun bindBook() {
        binding.bookAuthorName.text =
            sharedViewModel.currentBook.value?.author_name?.get(0).toString()
        binding.bookDetailName.text = sharedViewModel.currentBook.value?.title.toString()
        sharedViewModel.currentBook.value?.let {
            //Load the image from web service using Coil
            ImageLoader.loadImage(binding.bookDetailImage, it.cover_i, ImageSize.L)
        }
    }


}


