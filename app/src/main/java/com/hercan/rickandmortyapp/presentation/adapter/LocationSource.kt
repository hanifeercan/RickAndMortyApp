package com.hercan.rickandmortyapp.presentation.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hercan.rickandmortyapp.data.RickAndMortyApi
import com.hercan.rickandmortyapp.domain.utils.Status
import com.hercan.rickandmortyapp.domain.utils.safeApiCall
import com.hercan.rickandmortyapp.presentation.model.LocationUIModel
import com.hercan.rickandmortyapp.presentation.model.toLocationUIModel
import com.hercan.rickandmortyapp.presentation.utils.Constants.Network.LOCATION_PAGES_SIZE
import kotlinx.coroutines.coroutineScope

class LocationSource(private val api: RickAndMortyApi) : PagingSource<Int, LocationUIModel>() {
    override fun getRefreshKey(state: PagingState<Int, LocationUIModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationUIModel> {

        return try {
            val page = params.key ?: 1
            val response = safeApiCall { api.getLocations(page) }
            var data: List<LocationUIModel> = emptyList()
            coroutineScope {
                response.collect {
                    data = if (it.status == Status.SUCCESS) {
                        it.data?.toLocationUIModel() ?: emptyList()
                    } else {
                        emptyList()
                    }
                }
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == LOCATION_PAGES_SIZE) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}