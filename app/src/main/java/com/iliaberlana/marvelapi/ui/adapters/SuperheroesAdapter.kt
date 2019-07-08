package com.iliaberlana.marvelapi.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.ui.commons.inflate
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter

class SuperheroesAdapter(
    private val presenter: MainPresenter
) : RecyclerView.Adapter<SuperheroesViewHolder>() {

    var superheroes: MutableList<Superheroe> = ArrayList()

    fun addAll(collection: Collection<Superheroe>) {
        superheroes.addAll(collection)
        notifyDataSetChanged()
    }

    fun clean() {
        superheroes.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroesViewHolder =
        SuperheroesViewHolder(parent.inflate(R.layout.superheroe_item), presenter)

    override fun onBindViewHolder(holder: SuperheroesViewHolder, position: Int) = holder.bind(superheroes[position])

    override fun getItemCount(): Int = superheroes.size
}
