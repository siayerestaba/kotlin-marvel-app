package com.iliaberlana.marvelapi.ui.model

import java.io.Serializable

data class MarvelSuperHeroe(
    val id : Int,
    val name : String,
    val description: String,
    val daysFormLastModify : Int,
    val imageUrl : String
) : Serializable
{

}