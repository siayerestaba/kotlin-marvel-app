package com.iliaberlana.marvelapi.framework

import com.iliaberlana.data.OrdenationDataSource
import com.iliaberlana.domain.Ordenation
import com.iliaberlana.marvelapi.MarvelApp

class OrdenationDataSource : OrdenationDataSource {

    override  fun saveOrdenation(ordenation: Ordenation) {
        MarvelApp.prefs.order = ordenation.typeOrdenation
    }

    override fun getOrdenation() : Ordenation {
        val orderSaved = MarvelApp.prefs.order
        if(orderSaved.isNullOrEmpty()) return Ordenation.MODIFIED

        return Ordenation.getOrdenationByType(orderSaved)
    }
}