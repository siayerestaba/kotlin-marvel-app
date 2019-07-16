package com.iliaberlana.marvelapi

import android.app.Application
import com.iliaberlana.data.OrdenationRepository
import com.iliaberlana.data.SuperheroeRepository
import com.iliaberlana.marvelapi.framework.MarvelRepository
import com.iliaberlana.marvelapi.framework.OrdenationDataSource
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import com.iliaberlana.marvelapi.framework.prefs.Prefs
import com.iliaberlana.marvelapi.ui.MainActivity
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter
import com.iliaberlana.usecases.GetOrdenation
import com.iliaberlana.usecases.ListSuperheroes
import com.iliaberlana.usecases.SaveOrdenation
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class MarvelApp : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)

        startKoin{
            androidLogger()
            androidContext(this@MarvelApp)
            modules(appModule)
        }
    }

    private val appModule = module {
        single { MarvelClientService()}
        single<SuperheroeRepository> { MarvelRepository(get()) }
        single { ListSuperheroes(get()) }

        single<OrdenationRepository> { OrdenationDataSource() }
        single { GetOrdenation(get()) }
        single { SaveOrdenation(get()) }

        scope(named<MainActivity>()) {
            scoped { MainPresenter(get(), get(), get()) }
        }
    }
}