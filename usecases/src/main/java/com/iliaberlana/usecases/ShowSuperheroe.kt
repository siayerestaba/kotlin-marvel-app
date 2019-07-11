package com.iliaberlana.usecases

import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.domain.Superheroe

class ShowSuperheroe(
    private val superheroeRepository: SuperheroeRepository)
{
    suspend operator fun invoke(superheroeId: Int): Superheroe = superheroeRepository.showSuperheroe(superheroeId)
}