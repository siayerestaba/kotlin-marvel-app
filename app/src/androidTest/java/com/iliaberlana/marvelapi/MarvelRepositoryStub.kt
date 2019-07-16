package com.iliaberlana.marvelapi

import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.domain.Superheroe

class MarvelRepositoryStub : SuperheroeRepository {
    override suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> {
        return emptyList()
    }

    override suspend fun showSuperheroe(superheroeId: Int): Superheroe {
        return Superheroe(1, "","", "", "")
    }
}