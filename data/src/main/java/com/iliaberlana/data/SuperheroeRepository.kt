package com.iliaberlana.data

import com.iliaberlana.domain.Superheroe

class SuperheroeRepository (
    private val superheroeRemoteDataSource: SuperheroeRemoteDataSource,
    private val superheroeLocalDataSource: SuperheroeLocalDataSource
)
{
    suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> = superheroeRemoteDataSource.listSuperheroes(offset, limit, orderBy)

    fun showSuperheroe(superheroe: Superheroe): Superheroe = superheroeLocalDataSource.getSuperheroe(superheroe)
}

interface SuperheroeRemoteDataSource {
    suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe>
}

interface SuperheroeLocalDataSource {
    fun getSuperheroe(superheroe: Superheroe): Superheroe
}