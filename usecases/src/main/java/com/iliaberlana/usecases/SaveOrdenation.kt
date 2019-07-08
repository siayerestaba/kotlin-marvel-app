package com.iliaberlana.usecases

import com.iliaberlana.data.OrdenationRepository
import com.iliaberlana.domain.Ordenation

class SaveOrdenation(
    private val ordenationRepository: OrdenationRepository)
{
    operator fun invoke(ordenation: Ordenation) = ordenationRepository.saveOrdenation(ordenation)
}