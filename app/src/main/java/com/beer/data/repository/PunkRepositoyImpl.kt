package com.beer.data.repository

import com.beer.data.mapper.BeerMapper
import com.beer.data.remote.api.PunkApiService
import com.beer.domain.PunkRepository
import com.beer.domain.common.BaseResult
import com.beer.domain.dto.Beer
import javax.inject.Inject

class PunkRepositoyImpl @Inject constructor(
    private val apiService: PunkApiService,
    private val mapper: BeerMapper
) : PunkRepository {

    override suspend fun getBeersList(
        page: Int,
        perPage: Int
    ): BaseResult<List<Beer>> {
        val response = apiService.getBeersList(page, perPage)
        return if (response.isSuccessful) {
            BaseResult.Success(response.body()?.map {
                mapper.transform(it)
            })
        } else {
            return BaseResult.Error(Exception(response.message()))
        }
    }
}