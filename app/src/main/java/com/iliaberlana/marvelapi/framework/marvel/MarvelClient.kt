package com.iliaberlana.marvelapi.framework.marvel

import com.iliaberlana.marvelapi.framework.marvel.model.CharacterDataWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelClient {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String
    ): CharacterDataWrapper
}