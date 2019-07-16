package com.iliaberlana.marvelapi

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.iliaberlana.marvelapi.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class Main2InstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)


    @Test
    fun shows_ordenation_button() {
        activityRule.launchActivity(null)

        Espresso.onView(ViewMatchers.withId(R.id.action_order))
            .perform(ViewActions.click())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}