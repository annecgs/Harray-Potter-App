package com.example.frontend.sonserinaMembres

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.sonserinaMembres.SonserinaFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SonserinaFramentTest {
    @Test
    fun SonserinaFragmentTestInfo() {
        val scenario = launchFragmentInContainer<SonserinaFragment>()
        onView(ViewMatchers.withId(R.id.serchView)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.includeDivider)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.imageHouseSonserina)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.widgetListEmpty)).check(matches(isDisplayed()))
    }
}