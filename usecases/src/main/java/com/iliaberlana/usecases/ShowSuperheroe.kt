package com.iliaberlana.usecases

import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.domain.Superheroe

class ShowSuperheroe(
    private val superheroeRepository: SuperheroeRepository)
{

    operator fun invoke(superheroe: Superheroe): Superheroe = superheroeRepository.showSuperheroe(superheroe)
}