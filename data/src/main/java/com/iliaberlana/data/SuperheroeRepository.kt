package com.iliaberlana.data

import com.iliaberlana.domain.Superheroe

class SuperheroeRepository (
    private val superheroeDataSource: SuperheroeDataSource
)
{
    suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe> = superheroeDataSource.listSuperheroes(offset, limit, orderBy)
    fun showSuperheroe(superheroe: Superheroe): Superheroe = superheroeDataSource.getSuperheroe(superheroe)
}

interface SuperheroeDataSource {
    suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe>
    fun getSuperheroe(superheroe: Superheroe): Superheroe
}
