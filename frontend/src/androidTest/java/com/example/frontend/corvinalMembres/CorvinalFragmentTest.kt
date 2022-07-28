package com.example.frontend.corvinalMembres

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.corvinalMembres.CorvinalFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CorvinalFragmentTest {
    @Test
    fun CorvinalFragmentTestInfo() {
        val scenario = launchFragmentInContainer<CorvinalFragment>()
        onView(ViewMatchers.withId(R.id.serchView)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.includeDivider)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.imageHouseCorvinal)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.widgetListEmpty)).check(matches(isDisplayed()))
    }
}
