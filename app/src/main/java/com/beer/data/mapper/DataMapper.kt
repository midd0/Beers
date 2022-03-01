package com.beer.data.mapper

interface DataMapper<E, D> {
    fun transform(type: E): D
}
