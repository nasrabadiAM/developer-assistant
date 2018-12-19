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

package com.nasrabadiam.developerassistant.repo.android

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.nasrabadiam.developerassistant.repo.AppSummaryEntity
import io.reactivex.Observable
import java.util.*

class AndroidRepositoryImpl(private val context: Context) : AndroidRepository() {

    var packageManager: PackageManager = context.packageManager

    override fun getInstalledApps(): Observable<AppSummaryEntity> {
        return Observable.create {
            packageManager.getInstalledApplications(0)

            var apps: List<ApplicationInfo>? = packageManager.getInstalledApplications(0)

            if (apps == null) {
                apps = ArrayList()
            }

            // create corresponding apps and load their labels
            for (appInfo in apps) {
                val packageName = appInfo.packageName

                // only apps which are launchable
                if (context.packageManager.getLaunchIntentForPackage(packageName) != null) {
                    val name = packageManager.getApplicationLabel(appInfo)
                    val iconRes = appInfo.icon
                    val app =
                        AppSummaryEntity(name.toString(), packageName, iconRes)
                    it.onNext(app)
                }
            }
            it.onComplete()
        }
    }
}
