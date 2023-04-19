package com.hercan.rickandmortyapp.presentation.model

import com.hercan.rickandmortyapp.domain.models.GetLocationsResponseModel

data class LocationUIModel(
    val id: Int? = null,
    val name: String? = null,
    val residents: ArrayList<Int>? = null,
    val hasNextPage: Boolean = false
)

fun GetLocationsResponseModel.toLocationUIModel(): List<LocationUIModel>? {
    val hasNextPage = this.info?.next?.let { true } ?: false
    return this.results?.map {
        val id = it?.id
        val residents = it?.residents?.let { getIdsFromLinkList(it) }
        val name = it?.name
        LocationUIModel(id, name, residents, hasNextPage)
    }
}

fun getIdsFromLinkList(links: List<String>): ArrayList<Int> {
    val idList = arrayListOf<Int>()
    links.map {
        val id = it.split("/").last().filter { it.isDigit() }
        if (id.isNotEmpty()) {
            idList.add(id.toInt())
        }
    }
    return idList
}