package com.iliaberlana.data

import com.iliaberlana.domain.Ordenation

class OrdenationRepository (
    private val ordenationDataSource: OrdenationDataSource
) {
    fun saveOrdenation(ordenation: Ordenation) = ordenationDataSource.saveOrdenation(ordenation)
    fun getOrdenation() : Ordenation = ordenationDataSource.getOrdenation()
}

interface OrdenationDataSource {
    fun saveOrdenation(ordenation: Ordenation)
    fun getOrdenation() : Ordenation
}