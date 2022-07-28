package com.example.frontend.error

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.error.ErrorFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorFragmentTest {
    @Test
    fun testError() {
        val scenario = launchFragmentInContainer<ErrorFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.imageError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mensagem))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_tryAgain))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
