package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arti.databinding.BookReaderFragmentBinding
import com.example.arti.ui.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookReaderFragment : Fragment() {
    private lateinit var binding: BookReaderFragmentBinding
    private val sharedViewModel: BooksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BookReaderFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}