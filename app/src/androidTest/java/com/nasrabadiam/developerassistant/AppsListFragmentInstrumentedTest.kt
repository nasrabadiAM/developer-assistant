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

package com.nasrabadiam.developerassistant

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.nasrabadiam.developerassistant.apps.ui.AppsListFragment
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class AppsListFragmentInstrumentedTest {

    @Test
    fun testFragmentCreationInstance() {
        val fragment = AppsListFragment.newInstance()
        assertNotNull(fragment)
    }

    @Test
    fun testShowRecyclerView() {
        // The "state" and "factory" arguments are optional.
        val fragmentArgs = Bundle().apply {}
        val factory = FragmentFactory()
        val scenario = launchFragmentInContainer<AppsListFragment>(
            fragmentArgs, factory
        )
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

}
