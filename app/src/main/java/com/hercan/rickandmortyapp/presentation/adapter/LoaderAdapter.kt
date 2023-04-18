package com.hercan.rickandmortyapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.rickandmortyapp.databinding.LoaderItemViewBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {

    class LoaderViewHolder(private val binding: LoaderItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding = LoaderItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return LoaderViewHolder(binding)
    }
}