package com.beer.domain

import com.beer.domain.common.BaseResult
import com.beer.domain.dto.Beer


interface PunkRepository {

    suspend fun getBeersList(
        page: Int,
        perPage: Int
    ): BaseResult<List<Beer>>
}