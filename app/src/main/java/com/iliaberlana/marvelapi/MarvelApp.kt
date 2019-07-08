package com.iliaberlana.marvelapi

import android.app.Application
import com.iliaberlana.marvelapi.framework.prefs.Prefs

class MarvelApp : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
    }
}