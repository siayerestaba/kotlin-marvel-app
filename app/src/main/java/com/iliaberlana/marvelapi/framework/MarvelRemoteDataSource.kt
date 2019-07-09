package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.SuperheroeRemoteDataSource
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import com.iliaberlana.marvelapi.framework.marvel.model.toSuperheroe
import java.net.SocketTimeoutException
import com.iliaberlana.marvelapi.framework.marvel.model.Character as MarvelCharacter

class MarvelRemoteDataSource : SuperheroeRemoteDataSource {

    private val marvelClientService: MarvelClientService = MarvelClientService()

    override suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> {
        try {
            val response = marvelClientService.getCharacters(offset, limit, orderBy)
            val results: MutableList<MarvelCharacter> = response.data.results
            return results.map { it.toSuperheroe() }
        } catch (socketTimeoutException: SocketTimeoutException) {

        }

        return emptyList()
    }
}