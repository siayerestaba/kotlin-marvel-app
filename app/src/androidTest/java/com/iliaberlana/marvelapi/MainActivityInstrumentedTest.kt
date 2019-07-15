package com.iliaberlana.marvelapi

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.core.internal.deps.guava.base.Joiner.on
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.iliaberlana.marvelapi.framework.marvel.MarvelClientService
import com.iliaberlana.marvelapi.ui.MainActivity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.koin.test.KoinTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.`when`
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest: KoinTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    //private val marvelClientService = mockk<MarvelClientService>()

/*    @Before
    fun setUp() {
        startKoin {
            module {
                single { marvelClientService }
            }
        }
    }

    @After
    fun after()
    {
        stopKoin()
    }*/

    //@Test
    fun shows_ordenation_button() {
        onView(withId(R.id.action_order))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        val marvelClientService = mock<MarvelClientService> {
            on { this.getCharacters((0, 20, "-modified") } doReturn GlobalScope.async { emptyList<>() }
        }

//        coEvery {
//            marvelClientService.getCharacters(0, 20, "-modified")
//        } returns emptyList()

        onView(ViewMatchers.withText("¯\\_(ツ)_/¯")).check(matches(isDisplayed()))

        Unit
    }


}
