package com.hercan.rickandmortyapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.FragmentHomeBinding
import com.hercan.rickandmortyapp.presentation.adapter.LoaderAdapter
import com.hercan.rickandmortyapp.presentation.adapter.LocationHorizontalPagingDataAdapter
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    lateinit var pagingAdapter: LocationHorizontalPagingDataAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
        bindViewModel()
    }

    private fun bindUI() {
        viewModel.getLocations()
        pagingAdapter = LocationHorizontalPagingDataAdapter()

        binding.rvLocation.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                header = LoaderAdapter(),
                footer = LoaderAdapter()
            )
        }

        pagingAdapter.addLoadStateListener {
            it.refresh.let {
                binding.progressBar.isVisible = it is LoadState.Loading
                binding.rvLocation.isVisible = it is LoadState.NotLoading
            }
        }
    }

    private fun bindViewModel() {
        viewModel.getLocations().observe(viewLifecycleOwner) {
            pagingAdapter.submitData(lifecycle, it)
        }
    }

}