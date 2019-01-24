/*
 *     This is the source code of developer-assistant project.
 *     Copyright (C)   Ali Nasrabadi<nasrabadiam@gmail.com>  2018-2019
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nasrabadiam.developerassistant.apps

import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.apps.detail.ui.AppDetailActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppDetailActivityInstrumentedTest {

    @get:Rule
    var mActivityRule =
        ActivityTestRule(
            AppDetailActivity::class.java,
            true, false
        )

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        val intent = AppDetailActivity.startWith(
            appContext,
            appContext.packageName, Uri.EMPTY
        )
        mActivityRule.launchActivity(intent)
    }

    @Test
    fun testGetArgs() {
        val extra = mActivityRule.activity.intent.extras
        val packageName = extra?.getString(AppDetailActivity.PACKAGE_NAME_KEY)
        Assert.assertEquals(packageName, appContext.packageName)
    }

    @Test
    fun testShowActionItem() {
        onView(withId(R.id.launch_parent))
            .check(matches(isDisplayed()))
        onView(withText(appContext.getString(R.string.launch)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLaunchApp() {
        onView(withId(R.id.launch_parent))
            .perform(click())
            .check(doesNotExist())
    }

    @Test
    fun testShowActivities() {
        onView(withId(R.id.launch_parent))
            .perform(click())
            .check(doesNotExist())
    }
}
