package com.iliaberlana.marvelapi.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.ui.commons.loadImage
import com.iliaberlana.marvelapi.ui.model.MarvelSuperHeroe
import com.iliaberlana.marvelapi.ui.presenters.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailPresenter.ViewDetail {

    companion object {
        private const val SUPER_HEROE_KEY = "super_heroe_key"

        fun open(activity: Activity, marvelSuperHeroe: MarvelSuperHeroe) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(SUPER_HEROE_KEY, marvelSuperHeroe)
            activity.startActivity(intent)
        }
    }

    private val presenter = DetailPresenter(
        this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val superheroe = intent?.extras?.getSerializable(SUPER_HEROE_KEY) as? MarvelSuperHeroe

        presenter.create(superheroe!!)
        renderActionbar(superheroe.name)
    }

    private fun renderActionbar(superHeroeName: String) {
        val actionbar = supportActionBar
        actionbar!!.title = if (superHeroeName.isEmpty()) resources.getString(R.string.emptyName) else superHeroeName
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun showLoading() {
        superheroes_progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        superheroes_progressbar.visibility = View.GONE
    }

    override fun showSuperheroe( marvelSuperHeroe: MarvelSuperHeroe) { // TODO
        superheroe_image.loadImage(marvelSuperHeroe.imageUrl)
        superheroe_description.text = if (marvelSuperHeroe.description.isEmpty()) resources.getString(R.string.emptyDesc) else marvelSuperHeroe.description
        superheroe_modified.text = resources.getQuantityString(R.plurals.days_last_modified, marvelSuperHeroe.daysFormLastModify, "${marvelSuperHeroe.daysFormLastModify}")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
