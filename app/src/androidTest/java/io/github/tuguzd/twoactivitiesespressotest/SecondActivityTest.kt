package io.github.tuguzd.twoactivitiesespressotest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SecondActivityTest {
    @Rule
    @JvmField
    var activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun sendMessage() {
        // Click on the button to go to the second activity
        onView(withId(R.id.button_main)).perform(ViewActions.click())
        // The text must be empty, because empty string was sent
        onView(withId(R.id.text_message)).check(matches(withText("")))

        // Fill text view with reply
        onView(withId(R.id.editText_second)).perform(ViewActions.typeText("Reply"))
        // Send the message from the second activity
        onView(withId(R.id.button_second)).perform(ViewActions.click())
        // In the main activity, reply must match the data in text view from the second activity
        onView(withId(R.id.text_message_reply)).check(matches(withText("Reply")))
    }
}
