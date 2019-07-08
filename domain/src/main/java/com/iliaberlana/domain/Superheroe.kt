package com.iliaberlana.domain

import java.io.Serializable

data class Superheroe (
    val id : Int,
    val name : String,
    val description: String,
    val daysFromLastModify : String,
    val imageUrl : String
//    val numOfComics : Int,
//    val numOfStories : Int,
//    val numOfEvents : Int,
//    val numOfSeries : Int
) : Serializable
{
    override fun toString(): String {
        return "SUPERHEROE {ID: $id, Name: $name, Description: $description, ImageURL: $imageUrl, DaysFromLastModify: $daysFromLastModify}"
    }
}