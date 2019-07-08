package com.iliaberlana.marvelapi.framework.marvel.model

data class CharacterDataContainer(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    var results: MutableList<Character>
)