package com.beer.domain.common

data class Data<RequestData>(
    var responseType: Status,
    var data: RequestData? = null,
    var error: Exception? = null
)

enum class Status { SUCCESS, ERROR, LOADING }