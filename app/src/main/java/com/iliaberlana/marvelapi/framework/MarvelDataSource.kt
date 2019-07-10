package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.SuperheroeDataSource
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import com.iliaberlana.marvelapi.framework.marvel.model.toSuperheroe
import com.iliaberlana.marvelapi.framework.marvel.model.Character as MarvelCharacter

class MarvelDataSource : SuperheroeDataSource {

    private val marvelClientService: MarvelClientService = MarvelClientService()

    override suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> {
        try {
            val response = marvelClientService.getCharacters(offset, limit, orderBy)
            val results: MutableList<MarvelCharacter> = response.data.results
            return results.map { it.toSuperheroe() }
        } catch (exception: Exception) {

        }

        return emptyList()
    }

    override fun getSuperheroe(superheroe: Superheroe): Superheroe {
        return superheroe
    }

}