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

package com.nasrabadiam.developerassistant.repo.android

import android.content.Context
import android.content.pm.PackageManager
import com.nasrabadiam.developerassistant.repo.AppSummaryEntity
import io.reactivex.Observable

class AndroidRepositoryImpl(private val context: Context) : AndroidRepository() {

    var packageManager: PackageManager = context.packageManager

    override fun getInstalledApps(): Observable<AppSummaryEntity> {
        return Observable.fromIterable(packageManager.getInstalledApplications(0))
            .filter { context.packageManager.getLaunchIntentForPackage(it.packageName) != null }
            .map {
                AppSummaryEntity(
                    packageManager.getApplicationLabel(it).toString(),
                    it.packageName, it.icon
                )
            }
    }
}
