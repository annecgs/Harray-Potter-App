package com.example.frontend.ui.activity.splashScreen

import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.* // ktlint-disable no-wildcard-imports
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.frontend.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeExistMembresTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashScreen::class.java)

    @Test
    fun homeExistMembresTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(3000)

        val imageView = onView(
            allOf(
                withId(R.id.imageHogwarts),
                withParent(
                    allOf(
                        withId(R.id.includeHeader),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.imagePeople),
                withParent(withParent(withId(R.id.rv_home))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.nomePeople),
                withText("Hermione Granger"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Hermione Granger")))

        val imageView3 = onView(
            allOf(
                withId(R.id.casa),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))

        val imageView4 = onView(
            allOf(
                withId(R.id.casa),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView4.check(matches(isDisplayed()))

        val imageView5 = onView(
            allOf(
                withId(R.id.imagePeople),
                withParent(withParent(withId(R.id.rv_home))),
                isDisplayed()
            )
        )
        imageView5.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.nomePeople),
                withText("Draco Malfoy"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Draco Malfoy")))

        val imageView6 = onView(
            allOf(
                withId(R.id.casa),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView6.check(matches(isDisplayed()))

        val imageView7 = onView(
            allOf(
                withId(R.id.casa),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView7.check(matches(isDisplayed()))

        val imageView8 = onView(
            allOf(
                withId(R.id.imagePeople),
                withParent(withParent(withId(R.id.rv_home))),
                isDisplayed()
            )
        )
        imageView8.check(matches(isDisplayed()))

        val imageView9 = onView(
            allOf(
                withId(R.id.imagePeople),
                withParent(withParent(withId(R.id.rv_home))),
                isDisplayed()
            )
        )
        imageView9.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.nomePeople),
                withText("Cedric Diggory"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Cedric Diggory")))

        val imageView10 = onView(
            allOf(
                withId(R.id.casa),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView10.check(matches(isDisplayed()))

        val imageView11 = onView(
            allOf(
                withId(R.id.imagePeople),
                withParent(withParent(withId(R.id.rv_home))),
                isDisplayed()
            )
        )
        imageView11.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.nomePeople),
                withText("Cho Chang"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Cho Chang")))

        val imageView12 = onView(
            allOf(
                withId(R.id.casa),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView12.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.fab),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val button2 = onView(
            allOf(
                withId(R.id.fab),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))
    }
}
