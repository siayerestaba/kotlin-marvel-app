package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import com.iliaberlana.marvelapi.ui.model.emptySuperheroe

class MarvelRepository(
    private val marvelClientService: MarvelClientService
) : SuperheroeRepository {

    override suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> =
        marvelClientService.getCharacters(offset, limit, orderBy)

    override suspend fun showSuperheroe(superheroeId: Int): Superheroe =
        marvelClientService.getCharacter(superheroeId) ?: emptySuperheroe()
}