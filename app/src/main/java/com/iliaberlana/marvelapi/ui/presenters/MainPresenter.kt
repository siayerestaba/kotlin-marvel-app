package com.iliaberlana.marvelapi.ui.presenters

import com.iliaberlana.domain.Ordenation
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.R
import com.iliaberlana.usecases.GetOrdenation
import com.iliaberlana.usecases.ListSuperheroes
import com.iliaberlana.usecases.SaveOrdenation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
    private var view: ViewMain?,
    private val listSuperheroes: ListSuperheroes,
    private val getOrdenation: GetOrdenation,
    private val saveOrdenation: SaveOrdenation
) {
    private val limit: Int = 100 // TODO Parametrizar
    private var offset: Int = 0
    private lateinit var orderBy: Ordenation

    fun create() {
        view?.showLoading()
        getSavedOrdenation()
        renderSuperheroes()
    }

    private fun renderSuperheroes() {
        GlobalScope.launch(Dispatchers.Main) {
            val superheroes = withContext(Dispatchers.IO) { listSuperheroes(offset, limit, orderBy.typeOrdenation) }
            view?.hideLoading()

            when {
                superheroes.isEmpty() -> view?.showEmptyCase()
                else -> {
                    view?.hideEmptyCase()
                    view?.listSuperheroes(superheroes)
                }
            }

        }
    }

    private fun getSavedOrdenation() {
        orderBy = getOrdenation()
    }

//    fun getSuperheroesWithOrdenation(ordenation: Ordenation) {
//        if (ordenation != orderBy) {
//            view?.showLoading()
//            view?.cleanSuperheroes()
//
//            orderBy = ordenation
//            renderSuperheroes()
//            saveNewOrdenation(ordenation)
//        } else {
//            view?.showToastMessage(R.string.order_selected)
//        }
//    }

    fun getSuperheroesWithOrdenation(): Int {
        var iconId: Int

        view?.showLoading()
        view?.cleanSuperheroes()

        when (orderBy) {
            Ordenation.NAME -> {
                orderBy = Ordenation.MODIFIED
                iconId = R.mipmap.orderby_alphabet
            }

            Ordenation.MODIFIED -> {
                orderBy = Ordenation.NAME
                iconId = R.mipmap.orderby_date
            }
        }

        renderSuperheroes()
        saveNewOrdenation(orderBy)

        return iconId
    }

    private fun saveNewOrdenation(ordenation: Ordenation) {
        saveOrdenation(ordenation)
    }

    fun onSuperHeroClicked(superHero: Superheroe) = view?.showSuperheroe(superHero)

    fun onDestroy() {
        view = null
    }

    interface ViewMain {
        fun hideLoading()
        fun showLoading()

        fun listSuperheroes(superheroes: List<Superheroe>)
        fun showSuperheroe(superheroe: Superheroe)
        fun cleanSuperheroes()

        fun showToastMessage(stringId: Int)

        fun showEmptyCase()
        fun hideEmptyCase()
    }
}