package com.example.arti.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.arti.R
import com.example.arti.databinding.DetailsFragmentBinding
import com.example.arti.model.OrderViewModel


class DetailsFragment : Fragment() {

    companion object {
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

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

        // Create the observer which updates the UI.
        val priceObserver = Observer<Int> {
                newName -> binding?.bookPrice?.text = newName.toString()
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        sharedViewModel.currentBookPrice.observe(viewLifecycleOwner, priceObserver)

        binding?.bookDetailImage?.setImageResource(sharedViewModel.currentBookImageId)
        binding?.bookAuthorName?.text = getString(sharedViewModel.currentBookAuthorId)
        binding?.bookDetailName?.text = getString(sharedViewModel.currentBookNameId)
        binding?.bookPrice?.text = getString(R.string._200_uah, sharedViewModel.currentBookPrice.value.toString())

        binding?.buyButton?.setOnClickListener() {
            if (sharedViewModel.currentBookPrice.value?.toInt() == sharedViewModel.firstBookPrice) {
                sharedViewModel.currentBookPrice.value?.let {
                        bookPrice -> sharedViewModel.makeDiscount(bookPrice) }
            }
            goToOrderScreen()
        }

        binding?.readButton?.setOnClickListener() {
            val queryUrl: Uri = Uri.parse(
                    SEARCH_PREFIX +
                            getString(sharedViewModel.currentBookAuthorId) + " " +
                            getString(sharedViewModel.currentBookNameId)
            )
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
                startActivity(intent)
            }
        }
    }

    private fun goToOrderScreen() {
        findNavController().navigate(R.id.action_detailsFragment_to_orderDetailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
