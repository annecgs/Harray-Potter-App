package com.example.frontend.splashScreen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.frontend.R
import com.example.frontend.ui.activity.splashScreen.SplashScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenTest {
    @get: Rule
    val screenTest = ActivityTestRule<SplashScreen>(SplashScreen::class.java)

    @Test
    fun SplashScreenTestInfo() {
        onView(withId(R.id.imageSplasScreen)).check(matches(isDisplayed()))
    }
}
