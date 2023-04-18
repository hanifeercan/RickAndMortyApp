package com.hercan.rickandmortyapp.domain.utils

import android.util.Log
import com.hercan.rickandmortyapp.domain.base.BaseResponseModel
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
            emit(Resource.error(Status.ERROR, "İnternet Hatası!"))
        }
    }.catch { exception ->
        Log.d("BaseRepository", exception.localizedMessage.toString())
    }
}