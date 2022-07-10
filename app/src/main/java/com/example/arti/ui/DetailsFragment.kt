package com.example.arti.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.arti.databinding.DetailsFragmentBinding
import com.example.arti.model.OrderViewModel

class DetailsFragment : Fragment() {

    // Binding object instance with access to the views in the .xml layout
    private var binding: DetailsFragmentBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Create a ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = DetailsFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.bookDetailImage?.setImageResource(sharedViewModel.currentBookImageId)
        binding?.bookDetailName?.text = getString(sharedViewModel.currentBookNameId)
        binding?.bookPrice?.text = (sharedViewModel.currentBookPrice).toString()
    }

}
