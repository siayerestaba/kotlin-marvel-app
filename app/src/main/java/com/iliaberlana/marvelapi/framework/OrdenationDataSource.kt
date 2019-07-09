package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.OrdenationDataSource
import com.iliaberlana.domain.Ordenation
import com.iliaberlana.marvelapi.MarvelApp

class OrdenationDataSource : OrdenationDataSource {

    override  fun saveOrdenation(ordenation: Ordenation) {
        MarvelApp.prefs.order = ordenation.typeOrdenation
    }

    override fun getOrdenation() : Ordenation = Ordenation.getOrdenationByType(MarvelApp.prefs.order)
}