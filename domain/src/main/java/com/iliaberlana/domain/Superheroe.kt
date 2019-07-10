package com.iliaberlana.domain

data class Superheroe (
    val id : Int,
    val name : String,
    val description: String,
    val lastModify : String,
    val imageUrl : String
)
{
    override fun toString(): String {
        return "SUPERHEROE {ID: $id, Name: $name, Description: $description, ImageURL: $imageUrl, LastModify: $lastModify}"
    }
}