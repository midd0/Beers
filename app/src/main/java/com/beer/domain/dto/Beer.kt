package com.beer.domain.dto

const val NOT_FOUND = "Not found"
const val EMPTY = ""
const val NO_IMAGE = "NO IMAGE"
const val DEFAULT_INT = 0
const val DEFAULT_FLOAT = 0f

class Beer(
    val id: Int = DEFAULT_INT,
    val name: String = NOT_FOUND,
    val description: String = EMPTY,
    val tagline: String = EMPTY,
    val imageURL: String = NO_IMAGE,
    val abv: Float = DEFAULT_FLOAT,
    val ibu: Float = DEFAULT_FLOAT
) {
    var abvScreen: String
        get() {
            return abv.toString().plus("%")
        }
        set(newName) {
            abvScreen = newName
        }
}