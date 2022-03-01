package com.beer.data.remote.dto

import com.google.gson.annotations.SerializedName


data class Volume(

    @SerializedName("value") var value: Float? = null,
    @SerializedName("unit") var unit: String? = null

)