package com.iliaberlana.usecases

import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.domain.Ordenation
import com.iliaberlana.domain.Superheroe

class ListSuperheroes (
    private val superheroeRepository: SuperheroeRepository)
{
    operator fun invoke(offset: Int, limit: Int, orderBy: String): List<Superheroe> = superheroeRepository.listSuperheroes(offset, limit, orderBy)
}