package com.example.arti.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.arti.R
import com.example.arti.databinding.OrderDetailsFragmentBinding
import com.example.arti.ui.viewmodel.OrderViewModel


class OrderDetailsFragment: Fragment() {

    // Binding object instance with access to the views in the .xml layout
    private var binding: OrderDetailsFragmentBinding? = null

    // Implementing if LiveData in fragment
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Create a ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = OrderDetailsFragmentBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        sharedViewModel.currentBook.observe(this.viewLifecycleOwner) {
            // Updates the UI of detailed fragment
            binding?.authorInOrder?.text = getString(it.bookAuthorId)
            binding?.bookInOrder?.text = getString(it.bookNameId)
            binding?.priceInOrder?.text = getString(R.string._200_uah, sharedViewModel.currentBookPrice.value)
        }

        // Back to start screen and reset the price value
        binding?.cancelButton?.setOnClickListener() {
            goToStartScreen()
            sharedViewModel.resetValues()
        }

        binding?.sendButton?.setOnClickListener() {
            sendOrder()
        }
    }
    // Opens e-mail and fulfill it with order information
    private fun sendOrder() {
        val orderSummary = getString(
            R.string.mail_text,
            getString(sharedViewModel.currentBook.value?.bookNameId ?: 0),
            getString(sharedViewModel.currentBook.value?.bookAuthorId ?: 0),
            sharedViewModel.currentBookPrice.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject))
            putExtra(Intent.EXTRA_TEXT, orderSummary)
        }
        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }

    private fun goToStartScreen() {
        findNavController().navigate(R.id.action_orderDetailsFragment_to_listFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}