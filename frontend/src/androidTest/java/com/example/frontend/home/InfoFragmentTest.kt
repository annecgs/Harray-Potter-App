package com.example.frontend.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.home.InfoFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoFragmentTest {
    @Test
    fun TestInfo() {
        val scenario = launchFragmentInContainer<InfoFragment>()
        onView(withId(R.id.btn_addFavorite)).perform(click())
        onView(withId(R.id.imagePeople)).check(matches(isDisplayed()))
        // onView(withId(R.id.iv_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.casaIcon)).check(matches(isDisplayed()))
        onView(withId(R.id.textView3)).check(matches(isDisplayed()))
        onView(withId(R.id.iconOficio)).check(matches(isDisplayed()))
        onView(withId(R.id.iconNascimento)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_dataNascimento)).check(matches(isDisplayed()))
        onView(withId(R.id.iconGenero)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genero)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_return)).perform(click())
    }
}
