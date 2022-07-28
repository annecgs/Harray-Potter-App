package com.example.frontend.grifinoriaMembres

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.grifinoriaMembres.GrifinoriaFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GrifinoriaMembresTest {
    @Test
    fun GrifinoriaMembresTestInfo() {
        val scenario = launchFragmentInContainer<GrifinoriaFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.imagePeople))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
