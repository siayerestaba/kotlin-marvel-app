package com.iliaberlana.marvelapi.ui.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.ui.commons.loadImage
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.superheroe_item.view.*

class SuperheroesViewHolder(
    override val containerView: View,
    private val presenter: MainPresenter
) : RecyclerView.ViewHolder(containerView), LayoutContainer

{
    fun bind(superheroe: Superheroe) {
        containerView.superheroe_name.text = superheroe.name
        containerView.superheroe_image.loadImage(superheroe.imageUrl)

        setListeners(superheroe)
    }

    private fun setListeners(superheroe: Superheroe) {
        containerView.setOnClickListener { presenter.onSuperHeroClicked(superheroe) }
    }
}