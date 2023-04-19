package com.hercan.rickandmortyapp.data

import com.hercan.rickandmortyapp.domain.models.GetLocationsResponseModel
import com.hercan.rickandmortyapp.domain.models.GetResidentsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): Response<GetLocationsResponseModel>

    @GET("character")
    suspend fun getResidents(@Query("ids") ids: ArrayList<Int>): Response<GetResidentsResponseModel>
}