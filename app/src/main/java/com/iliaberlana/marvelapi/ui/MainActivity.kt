package com.iliaberlana.marvelapi.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.ui.adapters.SuperheroesAdapter
import com.iliaberlana.marvelapi.ui.commons.toast
import com.iliaberlana.marvelapi.ui.model.MarvelSuperheroeForList
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope


class MainActivity : AppCompatActivity(), MainPresenter.ViewMain {

    private val presenter: MainPresenter by currentScope.inject()
    private lateinit var adapter: SuperheroesAdapter

    private lateinit var menu: Menu

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.create()

        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        adapter = SuperheroesAdapter(presenter, {presenter.getMoreSuperheroes()})

        val layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
        layoutManager.gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        superheroes_recyclerview.adapter = adapter
        superheroes_recyclerview.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu =  menu!!

        presenter.calculateIconMenu()

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_order -> {
                presenter.getSuperheroesWithOrdenation()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun changeIconMenu(iconId: Int) {
        menu.getItem(0).setIcon(iconId)
    }

    override fun listSuperheroes(marvelSuperHeroes: List<MarvelSuperheroeForList>) {
        adapter.addAll(marvelSuperHeroes)
    }

    override fun cleanSuperheroes() {
        adapter.clean()
    }

    override fun showEmptyCase() {
        superheroe_text.visibility = View.VISIBLE
    }

    override fun hideEmptyCase() {
        superheroe_text.visibility = View.GONE
    }

    override fun showSuperheroe(marvelSuperHeroeId: Int) {
        DetailActivity.open(activity = this, marvelSuperHeroeId = marvelSuperHeroeId)
    }

    override fun showToastMessage(stringId: Int) {
        this.toast(this, resources.getString(stringId))
    }

    override fun showLoading() {
        superheroes_progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        superheroes_progressbar.visibility = View.GONE
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
