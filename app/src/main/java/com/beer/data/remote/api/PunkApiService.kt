package com.beer.data.remote.api

import com.beer.data.remote.dto.PunkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApiService {

    @GET("v2/beers")
    suspend fun getBeersList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<PunkResponse>>
}