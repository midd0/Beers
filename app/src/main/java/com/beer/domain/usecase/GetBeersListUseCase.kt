package com.beer.domain.usecase

import com.beer.domain.PunkRepository
import com.beer.domain.common.BaseResult
import com.beer.domain.dto.Beer
import javax.inject.Inject

class GetBeersListUseCase @Inject constructor(private val repository: PunkRepository) {
    suspend fun invoke(
        page: Int,
        perPage: Int
    ): BaseResult<List<Beer>> {
        return repository.getBeersList(page, perPage)
    }
}