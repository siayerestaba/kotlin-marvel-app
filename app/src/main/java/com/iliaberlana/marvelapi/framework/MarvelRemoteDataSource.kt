package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.SuperheroeRemoteDataSource
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import com.iliaberlana.marvelapi.framework.marvel.model.toSuperheroe
import java.net.SocketTimeoutException
import com.iliaberlana.marvelapi.framework.marvel.model.Character as MarvelCharacter

class MarvelRemoteDataSource : SuperheroeRemoteDataSource {

    private val marvelClientService : MarvelClientService =
        MarvelClientService()

    override fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> {
        val callResponse = marvelClientService.getCharacters(offset, limit, orderBy)

        try {
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataResponse = response.body()

                val results: MutableList<MarvelCharacter> = dataResponse?.data?.results ?: mutableListOf()
                return results.map { it.toSuperheroe() }
            } else {
                // TODO Error (QUE CUTRE!!)
            }
        } catch (socketTimeoutException : SocketTimeoutException) {

        }

        return emptyList()
    }
}