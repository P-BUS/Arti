package com.example.arti.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.arti.R
import com.example.arti.databinding.DetailsFragmentBinding
import com.example.arti.ui.viewmodel.OrderViewModel


class DetailsFragment : Fragment() {

    companion object {
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    // Binding object instance with access to the views in the .xml layout
    private var binding: DetailsFragmentBinding? = null

    // Implementing if LiveData in fragment
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Create a ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create the observer which updates the UI.
        sharedViewModel.currentBook.observe(this.viewLifecycleOwner) {
            // Updates the UI of detailed fragment
            binding?.bookDetailImage?.setImageResource(it.bookImageId)
            binding?.bookAuthorName?.text = getString(it.bookAuthorId)
            binding?.bookDetailName?.text = getString(it.bookNameId)
            binding?.bookPrice?.text = getString(R.string._200_uah, sharedViewModel.currentBookPrice.value.toString())
        }

        // When push the button discount implemented and transfer to details screen
        binding?.buyButton?.setOnClickListener() {
            // Checks the price to avoid double discount implementation
            if (sharedViewModel.currentBookPrice.value == sharedViewModel.currentBook.value?.bookPrice) {
                sharedViewModel.currentBookPrice.value.let {
                        bookPrice ->
                    if (bookPrice != null) {
                        sharedViewModel.makeDiscount(bookPrice)
                    }
                }
            }
            goToOrderScreen()
        }

        // When push the button Google search opens in browser with request to open current book information
        binding?.readButton?.setOnClickListener() {
            val queryUrl: Uri = Uri.parse(
                    SEARCH_PREFIX +
                            sharedViewModel.currentBook.value?.bookAuthorId?.let { it1 -> getString(it1) } + " " +
                            sharedViewModel.currentBook.value?.bookNameId?.let { it1 -> getString(it1) }
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
