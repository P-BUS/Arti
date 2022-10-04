package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.arti.R
import com.example.arti.databinding.DetailsFragmentBinding
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
            loadCover(binding.bookDetailImage, it.cover_i) }
    }
    // TODO find a way to make it reusable
    fun loadCover(imgView: ImageView, imgCode: Int) {
        val imgUrl = "https://covers.openlibrary.org/b/id/$imgCode-L.jpg"
        imgUrl.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    }


