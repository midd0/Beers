package com.beer.data.remote.dto

import com.google.gson.annotations.SerializedName


data class MashTemp(

    @SerializedName("temp") var temp: Temp? = Temp(),
    @SerializedName("duration") var duration: Float? = null

)