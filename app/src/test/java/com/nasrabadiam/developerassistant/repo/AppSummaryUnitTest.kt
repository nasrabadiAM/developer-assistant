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

package com.nasrabadiam.developerassistant.repo

import org.junit.Assert
import org.junit.Test

class AppSummaryUnitTest {

    companion object {
        const val NAME = "app name"
        const val PACKAGE_NAME = "com.example"
        const val ICON_RES_ID = 164
    }

    @Test
    fun test_map_to_domain_model() {
        val appSummary = AppSummaryEntity(NAME, PACKAGE_NAME, ICON_RES_ID)
        val domainModel = appSummary.getDomainModel()
        Assert.assertEquals(NAME, domainModel.name)
        Assert.assertEquals(PACKAGE_NAME, domainModel.packageName)
        Assert.assertEquals(ICON_RES_ID, domainModel.iconResId)
    }
}
