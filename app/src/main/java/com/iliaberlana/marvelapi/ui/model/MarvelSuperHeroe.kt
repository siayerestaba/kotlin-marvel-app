package com.iliaberlana.marvelapi.ui.model

data class MarvelSuperheroeForList(
    val id : Int,
    val name : String,
    val imageUrl : String
)

data class MarvelSuperheroeForDetail(
    val id : Int,
    val name : String,
    val description: String,
    val daysFormLastModify : Int,
    val imageUrl : String
)
