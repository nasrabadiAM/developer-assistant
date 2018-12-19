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

package com.nasrabadiam.developerassistant.apps.ui

import android.net.Uri
import com.nasrabadiam.developerassistant.Util
import com.nasrabadiam.developerassistant.apps.AppSummary

data class AppListItem(
    val name: String,
    val packageName: String,
    val iconUri: Uri
) {

    companion object {
        fun of(appSummary: AppSummary): AppListItem {
            return AppListItem(
                appSummary.name, appSummary.packageName,
                Util.getUriFromResourceId(appSummary.packageName, appSummary.iconResId)
            )
        }
    }

    fun compareTo(item: AppListItem): Int {
        return if (item.name == this.name &&
            item.packageName == this.packageName &&
            item.iconUri == this.iconUri
        ) {
            0
        } else {
            1
        }
    }
}