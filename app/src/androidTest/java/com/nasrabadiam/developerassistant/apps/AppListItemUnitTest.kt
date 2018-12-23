/*
 *     This is the source code of developer-assistant project.
 *     Copyright (C)   Ali Nasrabadi<nasrabadiam@gmail.com>  2018-2018
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

import com.nasrabadiam.developerassistant.AndroidUtil
import com.nasrabadiam.developerassistant.apps.ui.AppListItem
import org.junit.Assert
import org.junit.Test

class AppListItemUnitTest {

    companion object {
        const val NAME = "app name"
        const val PACKAGE_NAME = "com.example"
        const val ICON_RES_ID = 164
    }

    private val appSummary = AppSummary(NAME, PACKAGE_NAME, ICON_RES_ID)

    @Test
    fun test_map_domain_to() {
        val appListItem = AppListItem.of(appSummary)
        Assert.assertEquals(NAME, appListItem.name)
        Assert.assertEquals(PACKAGE_NAME, appListItem.packageName)
    }

    @Test
    fun test_get_uri_from_icon_id_in_AppListItem() {
        val appListItem = AppListItem.of(appSummary)
        Assert.assertEquals("android.resource://com.example/164", appListItem.iconUri.toString())
    }

    @Test
    fun test_get_uri_from_icon_id() {
        val uriString = AndroidUtil.getUriFromResourceId(PACKAGE_NAME, ICON_RES_ID)
        Assert.assertEquals("android.resource://com.example/164", uriString.toString())
    }

}
