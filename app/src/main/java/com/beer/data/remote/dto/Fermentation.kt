package com.beer.data.remote.dto

import com.google.gson.annotations.SerializedName


data class Fermentation (

  @SerializedName("temp" ) var temp : Temp? = Temp()

)