package com.iliaberlana.marvelapi.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.ui.commons.inflate
import com.iliaberlana.marvelapi.ui.model.MarvelSuperHeroe
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter

class SuperheroesAdapter(
    private val presenter: MainPresenter,
    val fetchNewPage: () -> Unit
) : RecyclerView.Adapter<SuperheroesViewHolder>() {

    private var distance: Int = 6
    private var waitingForNextPage: Boolean = false

    private var marvelSuperheroes: MutableList<MarvelSuperHeroe> = ArrayList()

    fun addAll(collection: Collection<MarvelSuperHeroe>) {
        setWaitingForNextPageFalse()
        marvelSuperheroes.addAll(collection)
        notifyDataSetChanged()
    }

    fun clean() {
        setWaitingForNextPageFalse()
        marvelSuperheroes.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (!waitingForNextPage) {
            if (position.plus(distance) >= itemCount) {
                setWaitingForNextPageTrue()
                fetchNewPage()
            }
        }

        return super.getItemViewType(position)
    }

    private fun setWaitingForNextPageFalse() {
        waitingForNextPage = false
    }

    private fun setWaitingForNextPageTrue() {
        waitingForNextPage = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder =
        SuperheroesViewHolder(parent.inflate(R.layout.superheroe_item), presenter)

    override fun onBindViewHolder(holder: SuperheroesViewHolder, position: Int) = holder.bind(marvelSuperheroes[position])

    override fun getItemCount(): Int = marvelSuperheroes.size
}
