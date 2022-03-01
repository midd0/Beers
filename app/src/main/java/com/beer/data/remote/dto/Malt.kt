package com.beer.data.remote.dto

import com.google.gson.annotations.SerializedName


data class Malt(

    @SerializedName("name") var name: String? = null,
    @SerializedName("amount") var amount: Amount? = Amount()

)