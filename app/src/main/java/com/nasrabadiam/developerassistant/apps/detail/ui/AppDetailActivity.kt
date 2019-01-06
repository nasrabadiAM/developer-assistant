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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppDetailActivity : AppCompatActivity() {

    var mPackageName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent?.extras?.containsKey(PACKAGE_NAME_KEY) == true) {
            mPackageName = intent?.extras?.getString(PACKAGE_NAME_KEY)!!
        }
    }

    companion object {
        const val PACKAGE_NAME_KEY = "PACKAGE_NAME"

        fun startWith(context: Context, packageName: String): Intent {
            val intent = Intent(context, AppDetailActivity::class.java)
            intent.putExtra(PACKAGE_NAME_KEY, packageName)
            return intent
        }
    }
}
