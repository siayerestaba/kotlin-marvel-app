package com.iliaberlana.marvelapi.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.iliaberlana.domain.Superheroe
import com.iliaberlana.marvelapi.R
import com.iliaberlana.marvelapi.ui.commons.loadImage
import com.iliaberlana.marvelapi.ui.presenters.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Interval

class DetailActivity : AppCompatActivity(), DetailPresenter.ViewDetail {

    companion object {
        private const val SUPER_HEROE_KEY = "super_heroe_key"

        fun open(activity: Activity, superHeroe: Superheroe) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(SUPER_HEROE_KEY, superHeroe)
            activity.startActivity(intent)
        }
    }

    private val presenter = DetailPresenter(
        this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val superheroe = intent?.extras?.getSerializable(SUPER_HEROE_KEY) as? Superheroe

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

    override fun showSuperheroe(superheroe: Superheroe) {
        superheroe_image.loadImage(superheroe.imageUrl)
        superheroe_description.text = if (superheroe.description.isEmpty()) resources.getString(R.string.emptyDesc) else superheroe.description

        val dt = DateTime(superheroe.daysFromLastModify)
        val now = DateTime()

        val interval = Days.daysBetween(dt.toLocalDate(), now.toLocalDate()).getDays()
        superheroe_modified.text = resources.getQuantityString(R.plurals.days_last_modified, interval, "$interval")

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
