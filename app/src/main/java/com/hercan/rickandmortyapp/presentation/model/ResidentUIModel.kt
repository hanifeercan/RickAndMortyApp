package com.hercan.rickandmortyapp.presentation.model

import android.os.Parcelable
import com.hercan.rickandmortyapp.domain.models.ItemListResponseModel
import com.hercan.rickandmortyapp.domain.models.ResidentItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResidentUIModel(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val originName: String?,
    val locationName: String?,
    val imageURL: String?,
    val episodes: List<String>?,
    val created: String?,
) : Parcelable

fun ItemListResponseModel<ResidentItem>.toResidentUIModel(): List<ResidentUIModel>? {
    return this.map {
        ResidentUIModel(
            id = it.id,
            name = it.name,
            status = it.status,
            species = it.species,
            gender = it.gender,
            originName = it.origin?.name,
            locationName = it.location?.name,
            imageURL = it.image,
            episodes = it.episode,
            created = it.created,
        )
    }
}

fun getEpisodesFromLinkList(links: List<String>?): ArrayList<Int> {
    val episodesList = arrayListOf<Int>()
    links?.map {
        val id = it.split("/").last().filter { it.isDigit() }
        if (id.isNotEmpty()) {
            episodesList.add(id.toInt())
        }
    }
    return episodesList
}