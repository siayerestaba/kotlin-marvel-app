package com.iliaberlana.data

import com.iliaberlana.domain.Ordenation

interface OrdenationRepository {
    fun saveOrdenation(ordenation: Ordenation)
    fun getOrdenation() : Ordenation
}