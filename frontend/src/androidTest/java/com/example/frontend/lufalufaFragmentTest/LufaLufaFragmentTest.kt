package com.example.frontend.lufalufaFragmentTest

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.lufalufaMembres.LufaLufaFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LufaLufaFragmentTest {
    @Test
    fun LufaLufaMembresTestInfo() {
        val scenario = launchFragmentInContainer<LufaLufaFragment>()
        onView(withId(R.id.serchView)).check(matches(isDisplayed()))
        onView(withId(R.id.includeDivider)).check(matches(isDisplayed()))
        onView(withId(R.id.widgetListEmpty)).check(matches(isDisplayed()))
        onView(withId(R.id.imageHouseLufaLufa)).check(matches(isDisplayed()))
    }
}