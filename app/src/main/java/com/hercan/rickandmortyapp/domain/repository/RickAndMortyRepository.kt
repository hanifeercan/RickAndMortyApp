package com.hercan.rickandmortyapp.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.hercan.rickandmortyapp.data.RickAndMortyApi
import com.hercan.rickandmortyapp.domain.models.GetResidentsResponseModel
import com.hercan.rickandmortyapp.domain.utils.Resource
import com.hercan.rickandmortyapp.domain.utils.safeApiCall
import com.hercan.rickandmortyapp.presentation.adapter.LocationSource
import com.hercan.rickandmortyapp.presentation.utils.Constants.Network.LOCATION_PAGES_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(private val api: RickAndMortyApi) {

    fun getLocations() =
        Pager(
            config = PagingConfig(pageSize = LOCATION_PAGES_SIZE),
            pagingSourceFactory = { LocationSource(api) }
        )

    suspend fun getResidents(ids: ArrayList<Int>): Flow<Resource<GetResidentsResponseModel>> {
        return safeApiCall { api.getResidents(ids) }
    }
}