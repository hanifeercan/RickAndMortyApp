package com.hercan.rickandmortyapp.presentation.model

import com.hercan.rickandmortyapp.domain.models.ItemListResponseModel
import com.hercan.rickandmortyapp.domain.models.ResidentItem

data class ResidentUIModel(
    val id: Int?,
    val name: String?,
    val status: String?,
    val gender: String?,
    val originName: String?,
    val locationName: String?,
    val imageURL: String?,
    val episodes: List<String?>?,
    val created: String?,
)

fun ItemListResponseModel<ResidentItem>.toResidentUIModel(): List<ResidentUIModel>? {
    return this.map {
        ResidentUIModel(
            id = it?.id,
            name = it?.name,
            status = it?.status,
            gender = it?.gender,
            originName = it?.origin?.name,
            locationName = it?.location?.name,
            imageURL = it?.image,
            episodes = it?.episode,
            created = it?.created,
        )
    }
}