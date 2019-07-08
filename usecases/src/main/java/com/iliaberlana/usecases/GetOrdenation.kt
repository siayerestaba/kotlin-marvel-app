package com.iliaberlana.usecases

import com.iliaberlana.data.OrdenationRepository
import com.iliaberlana.domain.Ordenation

class GetOrdenation(
    private val ordenationRepository: OrdenationRepository
)
{
    operator fun invoke(): Ordenation = ordenationRepository.getOrdenation()
}