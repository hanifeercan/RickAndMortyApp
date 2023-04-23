package com.hercan.rickandmortyapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hercan.rickandmortyapp.R
import com.hercan.rickandmortyapp.databinding.FragmentDetailBinding
import com.hercan.rickandmortyapp.presentation.model.getEpisodesFromLinkList
import com.hercan.rickandmortyapp.presentation.utils.capitalize
import com.hercan.rickandmortyapp.presentation.viewbinding.viewBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUI()
    }

    private fun bindUI() = with(binding) {
        arguments.let {
            val residentUIModel =
                it?.let { it1 -> DetailFragmentArgs.fromBundle(it1).residentUIModel }
            tvStatus.text = residentUIModel?.status?.capitalize()
            tvEpisodes.text = getEpisodesFromLinkList(residentUIModel?.episodes).joinToString()
            tvName.text = residentUIModel?.name?.uppercase(Locale.getDefault())?.capitalize()
            tvGender.text = residentUIModel?.gender?.capitalize()
            tvLocation.text = residentUIModel?.locationName?.capitalize()
            tvOrigin.text = residentUIModel?.originName?.capitalize()
            tvSpecy.text = residentUIModel?.species?.capitalize()
            residentUIModel?.imageURL.let {
                Picasso.get()
                    .load(it)
                    .into(binding.residentImage)
            }
            val sdf = SimpleDateFormat("dd MMMM yyyy, hh:mm:ss")
            val currentDate = sdf.format(Date())
            tvCreatedAt.text = currentDate
        }
        btnBack.setOnClickListener { findNavController().popBackStack() }
    }

}