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
import com.iliaberlana.data.OrdenationRepository
import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.framework.MarvelDataSource
import com.iliaberlana.marvelapi.framework.OrdenationDataSource
import com.iliaberlana.marvelapi.ui.adapters.SuperheroesAdapter
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter
import com.iliaberlana.usecases.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainPresenter.ViewMain {

    private val presenter: MainPresenter
    private lateinit var adapter: SuperheroesAdapter

    private lateinit var menu: Menu

    init {
        val marvelApiSource = MarvelDataSource()
        val superheroeRepository = SuperheroeRepository(marvelApiSource)

        val ordenationDataSource = OrdenationDataSource()
        val ordenationRepository = OrdenationRepository(ordenationDataSource)

        presenter = MainPresenter(
            this,
            ListSuperheroes(superheroeRepository),
            GetOrdenation(ordenationRepository),
            SaveOrdenation(ordenationRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.create()

        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        adapter = SuperheroesAdapter(presenter)

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

    override fun listSuperheroes(superheroes: List<Superheroe>) {
        adapter.addAll(superheroes)
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

    override fun showSuperheroe(superheroe: Superheroe) {
        DetailActivity.open(activity = this, superHeroe = superheroe)
    }

    override fun showToastMessage(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show()
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
