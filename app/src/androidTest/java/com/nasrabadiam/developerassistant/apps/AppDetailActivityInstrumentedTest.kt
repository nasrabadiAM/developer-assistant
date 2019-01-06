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

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nasrabadiam.developerassistant.MainActivity
import com.nasrabadiam.developerassistant.apps.detail.ui.AppDetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppDetailActivityInstrumentedTest {

    @get:Rule
    var mActivityRule =
        ActivityTestRule(AppDetailActivity::class.java)

    @Test
    fun testLaunchingActivity() {

    }
}
