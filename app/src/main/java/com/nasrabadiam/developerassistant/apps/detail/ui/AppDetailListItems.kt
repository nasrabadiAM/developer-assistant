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

package com.nasrabadiam.developerassistant.apps.detail.ui

import android.net.Uri
import com.nasrabadiam.developerassistant.apps.detail.AppAction
import com.nasrabadiam.developerassistant.apps.detail.AppInfo

sealed class AppDetailListItems

data class AppUiAction(val packageName: String, val iconUri: Uri) : AppDetailListItems() {
    companion object {
        fun valueOf(appAction: AppAction) =
            AppUiAction(appAction.packageName, Uri.parse(appAction.iconUriString))
    }
}

data class AppUiInfo(val title: String, val info: String) : AppDetailListItems() {
    companion object {
        fun valueOf(appInfo: AppInfo) = AppUiInfo(appInfo.title, appInfo.info)
    }
}

