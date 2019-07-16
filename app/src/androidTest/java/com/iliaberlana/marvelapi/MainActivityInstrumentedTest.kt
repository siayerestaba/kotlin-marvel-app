package com.iliaberlana.marvelapi

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.iliaberlana.marvelapi.framework.MarvelRepository
import com.iliaberlana.marvelapi.ui.MainActivity
import com.iliaberlana.marvelapi.ui.presenters.MainPresenter
import com.iliaberlana.usecases.ListSuperheroes
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest : AutoCloseKoinTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    val marvelRepository = mockk<MarvelRepository>()
    val listSuperheroes = ListSuperheroes(marvelRepository)

    @Before
    fun setUp() {
        coEvery { marvelRepository.listSuperheroes(0, 50, any()) } returns emptyList()

        loadKoinModules(
            test2Module
        )
    }

    val test2Module = module {
        single(override = true) { marvelRepository }
        single(override = true) { listSuperheroes }

        scope(named<MainActivity>()) {
            scoped(override = true) { MainPresenter(get(), get(), get()) }
        }
    }

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        activityRule.launchActivity(null)

        onView(withId(R.id.superheroe_text)).check(matches(isDisplayed()))

        Unit
    }
}
