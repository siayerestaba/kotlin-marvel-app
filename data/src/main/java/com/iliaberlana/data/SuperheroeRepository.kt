package com.iliaberlana.data

import com.iliaberlana.domain.Superheroe

interface SuperheroeRepository {
    suspend fun listSuperheroes(offset: Int, limit: Int, orderBy: String): List<Superheroe>
    suspend fun showSuperheroe(superheroeId: Int): Superheroe
}
