package com.hercan.rickandmortyapp.domain.repository

import com.hercan.rickandmortyapp.data.RickAndMortyApi
import com.hercan.rickandmortyapp.domain.base.BaseRepository
import com.hercan.rickandmortyapp.domain.models.GetLocationsResponseModel
import com.hercan.rickandmortyapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(private val api: RickAndMortyApi) :
    BaseRepository() {

    suspend fun getLocations(page: Int): Flow<Resource<GetLocationsResponseModel>> {
        return safeApiCall { api.getLocations(page) }
    }

}