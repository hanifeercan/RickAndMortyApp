package com.hercan.rickandmortyapp.data

import com.hercan.rickandmortyapp.domain.models.GetLocationsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): Response<GetLocationsResponseModel>

}