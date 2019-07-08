package com.iliaberlana.marvelapi.framework.marvel

import com.iliaberlana.marvelapi.framework.marvel.model.CharacterDataWrapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class MarvelClientService {
    private val URL_BASE = "https://gateway.marvel.com:443"
    private val PUBLIC_KEY = "12ff4a1c748b90b56d04980bd6309239"
    private val SECRET_KEY = "bac19de47a71b63240d8c83eb172134eb8cd58fb"

    private val marvelClient: MarvelClient
    private var timeStamp: String = Date().time.toString()

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor) // TODO Borrar producción
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        marvelClient = retrofit.create(MarvelClient::class.java)
    }

    fun getCharacters(offset: Int, limit: Int, orderBy: String): Call<CharacterDataWrapper> {
        val msa = getDataForMarvelServerAuthentication()
        return marvelClient.getCharacters(PUBLIC_KEY, limit, offset, timeStamp, msa, orderBy)
    }

    private fun getDataForMarvelServerAuthentication(): String {
        timeStamp = Date().time.toString()

        return (timeStamp + SECRET_KEY + PUBLIC_KEY).md5()
    }


}