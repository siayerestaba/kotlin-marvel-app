package com.iliaberlana.marvelapi.ui.presenters

import com.iliaberlana.domain.Ordenation
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.ui.model.MarvelSuperheroeForList
import com.iliaberlana.marvelapi.ui.model.toMarvelSuperheroeFromList
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
    private val limit: Int = 50
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
                superheroes.isEmpty() -> {
                    if(offset == 0) {
                        view?.showEmptyCase()
                    }
                }
                else -> {
                    view?.hideEmptyCase()

                    val listMarvelSuperheroe = superheroes.map { it.toMarvelSuperheroeFromList() }
                    view?.listSuperheroes(listMarvelSuperheroe)
                }
            }

        }
    }

    private fun getSavedOrdenation() {
        orderBy = getOrdenation()
    }

    fun getSuperheroesWithOrdenation() {
        view?.showLoading()
        view?.cleanSuperheroes()

        offset = 0

        when (orderBy) {
            Ordenation.NAME -> {
                orderBy = Ordenation.MODIFIED
            }

            Ordenation.MODIFIED -> {
                orderBy = Ordenation.NAME
            }
        }

        calculateIconMenu()
        renderSuperheroes()
        saveNewOrdenation(orderBy)
    }

    fun getMoreSuperheroes() {
        offset = offset.plus(limit)
        renderSuperheroes()
    }

    fun calculateIconMenu() {
        when (orderBy) {
            Ordenation.NAME -> {
                view?.changeIconMenu(R.mipmap.orderby_date)
            }

            Ordenation.MODIFIED -> {
                view?.changeIconMenu(R.mipmap.orderby_alphabet)
            }
        }
    }

    private fun saveNewOrdenation(ordenation: Ordenation) {
        saveOrdenation(ordenation)
    }

    fun onSuperHeroClicked(marvelSuperHeroeId: Int) = view?.showSuperheroe(marvelSuperHeroeId)

    fun onDestroy() {
        view = null
    }

    interface ViewMain {
        fun hideLoading()
        fun showLoading()

        fun listSuperheroes(marvelSuperHeroes:  List<MarvelSuperheroeForList>)
        fun showSuperheroe(marvelSuperHeroeId: Int)
        fun cleanSuperheroes()

        fun changeIconMenu(iconId: Int)

        fun showToastMessage(stringId: Int)

        fun showEmptyCase()
        fun hideEmptyCase()
    }
}