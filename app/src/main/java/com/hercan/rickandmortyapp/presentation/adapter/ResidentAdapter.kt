package com.hercan.rickandmortyapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hercan.rickandmortyapp.databinding.ResidentItemViewBinding
import com.hercan.rickandmortyapp.presentation.model.ResidentUIModel
import com.squareup.picasso.Picasso

class ResidentAdapter(private val onClickCallback: (resident: ResidentUIModel) -> Unit) :
    ListAdapter<ResidentUIModel, ResidentAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(
        private val binding: ResidentItemViewBinding,
        private val callback: (resident: ResidentUIModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResidentUIModel) {
            binding.name.text = item.name
            item.imageURL.let {
                Picasso.get()
                    .load(it)
                    .into(binding.residentImage)
            }
            item.gender.let {
                //todo: set icons
                if (it.equals("female")) {
                    //    holder.binding.genderImage.setImageResource(R.drawable.)
                } else if (it.equals("male")) {
                    //    holder.binding.genderImage.setImageResource(R.drawable.)
                } else if (it.equals("genderless")) {
                    //    holder.binding.genderImage.setImageResource(R.drawable.)
                } else if (it.equals("unknown")) {
                    //    holder.binding.genderImage.setImageResource(R.drawable.)
                }
            }
            binding.root.setOnClickListener {
                callback.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ResidentItemViewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding, onClickCallback)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }
}

object DiffCallback : DiffUtil.ItemCallback<ResidentUIModel>() {
    override fun areItemsTheSame(oldItem: ResidentUIModel, newItem: ResidentUIModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ResidentUIModel, newItem: ResidentUIModel): Boolean {
        return oldItem.id == newItem.id
    }
}