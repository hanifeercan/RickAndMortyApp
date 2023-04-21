package com.hercan.rickandmortyapp.data

import com.hercan.rickandmortyapp.domain.models.GetLocationsResponseModel
import com.hercan.rickandmortyapp.domain.models.ItemListResponseModel
import com.hercan.rickandmortyapp.domain.models.ResidentItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): Response<GetLocationsResponseModel>

    @GET("character/{characterIds}")
    suspend fun getResidents(@Path("characterIds") characterIds: ArrayList<Int>): Response<ItemListResponseModel<ResidentItem>>

}