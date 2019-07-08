package com.iliaberlana.marvelapi.framework.marvel.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Image
)

data class Image(
    val path: String,
    val extension: String
)

