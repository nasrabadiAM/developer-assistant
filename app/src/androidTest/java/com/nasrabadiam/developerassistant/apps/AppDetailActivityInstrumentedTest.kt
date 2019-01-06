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

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.apps.detail.ui.AppDetailActivity
import org.hamcrest.Matchers.not
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

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        val intent = AppDetailActivity.startWith(appContext, appContext.packageName)
        mActivityRule.launchActivity(intent)
    }

    @Test
    fun testGetArgs() {
        val extra = mActivityRule.activity.intent.extras
        val packageName = extra?.getString(AppDetailActivity.PACKAGE_NAME_KEY)
        Assert.assertEquals(packageName, appContext.packageName)
    }

    @Test
    fun testUiInitialState() {
        onView(withId(R.id.app_detail_message))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.app_detail_prent))
            .check(matches(isDisplayed()))
        onView(withId(R.id.app_detail_progressBar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.app_detail_recyclerView))
            .check(matches(not(isDisplayed())))
    }
}
