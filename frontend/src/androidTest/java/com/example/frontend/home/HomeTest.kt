package com.example.frontend.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.home.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {
    @Test
    fun TestHomeInfo() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.serchView)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.includeDivider)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.imageHogwarts)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.widgetListEmpty)).check(matches(isDisplayed()))
    }
}
