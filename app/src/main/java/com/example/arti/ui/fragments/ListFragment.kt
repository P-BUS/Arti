package com.example.arti.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arti.R
import com.example.arti.databinding.ListFragmentBinding
import com.example.arti.ui.adapters.BooksListAdapter
import com.example.arti.ui.viewmodel.BooksApiStatus
import com.example.arti.ui.viewmodel.BooksViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: ListFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel: BooksViewModel by activityViewModels()
    private var isLinearLayoutManager = true // Keeps track of which LayoutManager is in use

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        recyclerView = binding.recyclerView

        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.VISIBLE

        lifecycleScope.launch {
            sharedViewModel.isLinearLayout
                // repeatOnLifecycle() under the hood for single flow - for simplicity
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                // Diffing: Returns flow where all subsequent repetitions of the same value are filtered out.
                .distinctUntilChanged()
                .collect { value ->
                    isLinearLayoutManager = value
                    chooseLayout()
                    //Redraw the options menu
                    activity?.invalidateOptionsMenu()
                }
        }

        val adapter = BooksListAdapter { currentBook ->
            sharedViewModel.updateCurrentBook(currentBook)
            findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
        }
        recyclerView.adapter = adapter

        /*
        * Observe changes of books using State Flow
        * when fragment is on Started state and based on Coroutines
        */
        lifecycleScope.launch {
            sharedViewModel.books
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collect {
                    adapter.submitList(it)
                }
        }

        /*
        * Observe changes of BooksApiStatus loading using State Flow
        * when fragment is on Started state and based on Coroutines
        */
        lifecycleScope.launch {
            sharedViewModel.status
                // repeatOnLifecycle() under the hood for single flow - for simplicity
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                // Diffing: Returns flow where all subsequent repetitions of the same value are filtered out.
                .distinctUntilChanged() // TODO: maybe it is not needed here?
                .collect {
                    showLoadingImage()
                }
        }

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_layout, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_switch_layout -> {
                        // Sets isLinearLayoutManager (a Boolean) to the opposite value
                        isLinearLayoutManager = !isLinearLayoutManager
                        // Sets layout and icon
                        chooseLayout()
                        setIcon(menuItem)
                        // Launches a coroutine and write the layout setting in the preference Datastore
                        lifecycleScope.launch() {
                            sharedViewModel.saveLayoutToPreferencesStore(
                                isLinearLayoutManager
                                //requireContext()
                            )
                        }
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.STARTED)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(
                    this.requireContext(),
                    R.drawable.ic_baseline_view_module_24
                )
            else ContextCompat.getDrawable(
                this.requireContext(),
                R.drawable.ic_baseline_view_list_24
            )
    }

    /**
     * Sets the LayoutManager for the [RecyclerView] based on the desired orientation of the list.
     */
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun showLoadingImage() {
        when (sharedViewModel.status.value) {
            BooksApiStatus.LOADING -> {
                binding.statusProgressIndicator.visibility = View.VISIBLE
            }

            BooksApiStatus.DONE -> {
                binding.statusProgressIndicator.visibility = View.GONE
            }

            BooksApiStatus.ERROR -> {
                binding.statusProgressIndicator.visibility = View.VISIBLE
            }
        }
    }

    // Observe Work status
    /*    WorkManager.getInstance().getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(lifecycleOwner, Observer { workInfo ->
                // Check if the current work's state is "successfully finished"
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    displayImage(workInfo.outputData.getString(KEY_IMAGE_URI))
                }
            })*/

}


