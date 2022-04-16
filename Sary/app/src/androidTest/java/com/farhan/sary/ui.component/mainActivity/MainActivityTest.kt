package com.farhan.ui.component.recipes

import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.farhan.sary.R
import com.farhan.sary.DataStatus
import com.farhan.sary.TestUtil.dataStatus
import com.farhan.sary.ui.component.mainActivity.MainActivity
import com.farhan.sary.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, false, false)
    private var mIdlingResource: IdlingResource? = null

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun displayRecipesList() {
        dataStatus = DataStatus.Success
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.rootView)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun noData() {
        dataStatus = DataStatus.Fail
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.rootView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_no_data)).check(matches(isDisplayed()))
    }


    @Test
    fun testScroll() {
        dataStatus = DataStatus.Success
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.rootView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}
