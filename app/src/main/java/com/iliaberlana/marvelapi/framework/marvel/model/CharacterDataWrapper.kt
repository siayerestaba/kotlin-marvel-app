package com.iliaberlana.marvelapi.framework.marvel.model

data class CharacterDataWrapper(
    val code: String,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: CharacterDataContainer
)