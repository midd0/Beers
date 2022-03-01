package com.beer.data.mapper

import com.beer.data.remote.dto.PunkResponse
import com.beer.domain.dto.Beer
import javax.inject.Inject

class BeerMapper @Inject constructor() : DataMapper<PunkResponse, Beer> {

    override fun transform(type: PunkResponse): Beer =
        Beer(
            type.id,
            type.name,
            type.description,
            type.tagline,
            type.imageUrl,
            type.abv,
            type.ibu
        )
}