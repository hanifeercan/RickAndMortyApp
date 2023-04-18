package com.hercan.rickandmortyapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hercan.rickandmortyapp.databinding.LocationItemViewBinding
import com.hercan.rickandmortyapp.presentation.model.LocationUIModel

class LocationHorizontalPagingDataAdapter :
    PagingDataAdapter<LocationUIModel, LocationHorizontalPagingDataAdapter.ViewHolder>(diffCallback) {

    class ViewHolder(val binding: LocationItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.binding.name.text = it.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LocationItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<LocationUIModel>() {
                override fun areItemsTheSame(
                    oldItem: LocationUIModel,
                    newItem: LocationUIModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: LocationUIModel,
                    newItem: LocationUIModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }

}