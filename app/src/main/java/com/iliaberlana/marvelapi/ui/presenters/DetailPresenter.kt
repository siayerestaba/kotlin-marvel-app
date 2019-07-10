package com.iliaberlana.marvelapi.ui.presenters

import com.iliaberlana.marvelapi.ui.model.MarvelSuperHeroe

class DetailPresenter(
    private var view: ViewDetail?
) {

    fun create(marvelSuperHeroe: MarvelSuperHeroe) {
        view?.showLoading()
        renderSuperheroe(marvelSuperHeroe)
    }

    private fun renderSuperheroe(marvelSuperHeroe: MarvelSuperHeroe) {
        view?.hideLoading()
        view?.showSuperheroe(marvelSuperHeroe)
    }

    fun onDestroy() {
        view = null
    }

    interface ViewDetail {
        fun hideLoading()
        fun showLoading()
        fun showSuperheroe(marvelSuperHeroe: MarvelSuperHeroe)
    }
}
