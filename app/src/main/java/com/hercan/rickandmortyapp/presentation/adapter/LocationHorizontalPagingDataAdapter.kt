package com.hercan.rickandmortyapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.LocationItemViewBinding
import com.hercan.rickandmortyapp.presentation.model.LocationUIModel

class LocationHorizontalPagingDataAdapter :
    PagingDataAdapter<LocationUIModel, LocationHorizontalPagingDataAdapter.ViewHolder>(diffCallback) {
    private var selectedItemPosition: Int = -1
    private var clickListener: ((residents: ArrayList<Int>?) -> Unit)? = null

    inner class ViewHolder(private val binding: LocationItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocationUIModel, position: Int) {
            binding.name.text = item.name

            if (position == selectedItemPosition) {
                binding.name.setBackgroundResource(R.drawable.bg_selected_location)
            } else {
                binding.name.setBackgroundResource(R.drawable.bg_location)
            }

            binding.root.setOnClickListener {
                if (selectedItemPosition == position) {
                    return@setOnClickListener
                }
                clickListener?.invoke(item.residents)
                notifyItemChanged(selectedItemPosition) //previouslyPosition
                selectedItemPosition = position
                notifyItemChanged(selectedItemPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LocationItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (selectedItemPosition == -1 && holder.bindingAdapterPosition == 0) {
            selectedItemPosition = 0
            getItem(0)?.let { item ->
                clickListener?.invoke(item.residents)
            }
            holder.itemView.post {
                notifyItemChanged(selectedItemPosition)
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<LocationUIModel>() {
            override fun areItemsTheSame(
                oldItem: LocationUIModel, newItem: LocationUIModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LocationUIModel, newItem: LocationUIModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    fun setClickListener(listener: (residents: ArrayList<Int>?) -> Unit) {
        this.clickListener = listener
    }

}