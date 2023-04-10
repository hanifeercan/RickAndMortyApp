package com.hercan.rickandmortyapp.domain.base

import com.google.gson.annotations.SerializedName

open class BaseResponseModel {
    @SerializedName("error")
    val error: String? = null
}