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
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun sendMessage() {
        // Reply is empty on main application start
        onView(withId(R.id.text_message_reply)).check(matches(withText("")))
        // Fill text view with data
        onView(withHint(R.string.editText_main)).perform(ViewActions.typeText("Hello"))
        // Send the message from the main activity
        onView(withText(R.string.button_main)).perform(ViewActions.click())
        // In the second activity, message must match the data in text view from the main activity
        onView(withId(R.id.text_message)).check(matches(withText("Hello")))
    }

    @Test
    fun sendAndReceive() {
        onView(withId(R.id.text_message_reply)).check(matches(withText("")))
        onView(withHint(R.string.editText_main)).perform(ViewActions.typeText("How are you"))
        onView(withText(R.string.button_main)).perform(ViewActions.click())
        onView(withId(R.id.text_message)).check(matches(withText("How are you")))

        // In the second activity, reply back to the main one
        onView(withHint(R.string.editText_second)).perform(ViewActions.typeText("I'm fine"))
        // Send the message from the second activity
        onView(withText(R.string.button_second)).perform(ViewActions.click())
        // In the main activity, reply must match the data in text view from the second activity
        onView(withId(R.id.text_message_reply)).check(matches(withText("I'm fine")))
    }
}
