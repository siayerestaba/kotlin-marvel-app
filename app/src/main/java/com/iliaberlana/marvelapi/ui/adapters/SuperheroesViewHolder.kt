package com.iliaberlana.marvelapi.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import com.iliaberlana.marvelapi.ui.commons.loadImage
import com.iliaberlana.marvelapi.ui.model.MarvelSuperheroeForList
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.superheroe_item.view.*

class SuperheroesViewHolder(
    override val containerView: View,
    private val presenter: MainPresenter
) : RecyclerView.ViewHolder(containerView), LayoutContainer

{
    fun bind(marvelSuperHeroe: MarvelSuperheroeForList) {
        containerView.superheroe_name.text = marvelSuperHeroe.name
        containerView.superheroe_image.loadImage(marvelSuperHeroe.imageUrl)

        setListeners(marvelSuperHeroe)
    }

    private fun setListeners(marvelSuperHeroe: MarvelSuperheroeForList) {
        containerView.setOnClickListener { presenter.onSuperHeroClicked(marvelSuperHeroe.id) }
    }
}