package com.hercan.rickandmortyapp.domain.utils

import android.util.Log
import com.hercan.rickandmortyapp.domain.base.BaseResponseModel
import com.hercan.rickandmortyapp.domain.models.ItemListResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

suspend fun <T : BaseResponseModel> safeApiCall(call: suspend () -> Response<T>): Flow<Resource<T>> {
    return flow {
        val response = call.invoke()
        if (response.isSuccessful) {
            val model = response.body()!!
            if (model.error == null) {
                emit(Resource.success(model))
            } else {
                emit(
                    Resource.error(
                        Status.ERROR,
                        model.error
                    )
                )
            }
        } else {
            emit(
                Resource.error(
                    Status.ERROR,
                    "Opps! Something went wrong."
                )
            )
        }
    }.catch { exception ->
        Log.d("BaseRepository", exception.localizedMessage.toString())
    }
}

suspend fun <T : BaseResponseModel> safeApiCallForItemList(call: suspend () -> Response<ItemListResponseModel<T>>): Flow<Resource<ItemListResponseModel<T>>> {
    return flow {
        val response = call.invoke()
        if (response.isSuccessful) {
            val list = response.body()!!
            if (list.isNotEmpty() && list[0].error == null) {
                emit(Resource.success(list))
            } else {
                emit(
                    Resource.error(
                        Status.ERROR,
                        list[0].error ?: "Opps! Something went wrong."

                    )
                )
            }
        } else {
            emit(
                Resource.error(
                    Status.ERROR,
                    "Opps! Something went wrong."
                )
            )
        }
    }.catch { exception ->
        Log.d("BaseRepository", exception.localizedMessage.toString())
    }
}