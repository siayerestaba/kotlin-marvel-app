package com.iliaberlana.marvelapi.ui.presenters

import com.iliaberlana.domain.Superheroe

class DetailPresenter(
    private var view: ViewDetail?
) {

    fun create(superheroe: Superheroe) {
        view?.showLoading()
        renderSuperheroe(superheroe)
    }

    private fun renderSuperheroe(superheroe: Superheroe) {
        view?.hideLoading()
        view?.showSuperheroe(superheroe)
    }

    fun onDestroy() {
        view = null
    }

    interface ViewDetail {
        fun hideLoading()
        fun showLoading()
        fun showSuperheroe(superheroe: Superheroe)
    }
}
