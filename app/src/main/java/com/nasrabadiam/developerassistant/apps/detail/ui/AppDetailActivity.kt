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
import android.content.pm.PackageManager.MATCH_DEFAULT_ONLY
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nasrabadiam.developerassistant.BuildConfig
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.apps.AppsDomainImpl
import com.nasrabadiam.developerassistant.apps.detail.AppAction
import com.nasrabadiam.developerassistant.apps.detail.AppDetail
import com.nasrabadiam.developerassistant.apps.detail.AppInfo
import com.nasrabadiam.developerassistant.repo.RepositoryImpl
import com.nasrabadiam.developerassistant.repo.android.AndroidRepositoryImpl
import com.nasrabadiam.developerassistant.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.app_detail.*
import java.io.File


class AppDetailActivity : AppCompatActivity() {

    private lateinit var appDetailViewModel: AppDetailViewModel

    private lateinit var mPackageName: String
    private lateinit var mAppIconUri: Uri
    private val listAdapter = AppDetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_detail)

        app_detail_recyclerView.layoutManager = LinearLayoutManager(this)
        app_detail_recyclerView.setHasFixedSize(true)
        app_detail_recyclerView.adapter = listAdapter
        listAdapter.onItemClickListener = itemClickListener

        if (intent?.extras?.containsKey(PACKAGE_NAME_KEY) == true &&
            intent?.extras?.containsKey(ICON_URI_KEY) == true
        ) {
            mPackageName = intent?.extras?.getString(PACKAGE_NAME_KEY)!!
            mAppIconUri = intent?.extras?.getParcelable(ICON_URI_KEY)!!
        }

        val domain = AppsDomainImpl(RepositoryImpl(AndroidRepositoryImpl(this)))

        val viewModelFactory =
            ViewModelFactory(domain, AndroidSchedulers.mainThread())

        appDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AppDetailViewModel::class.java)

        appDetailViewModel.apply {
            getAppDetail(mPackageName, mAppIconUri.toString())
            appDetails.observe(this@AppDetailActivity, observer)
        }
    }

    private val observer = Observer<List<AppDetail>> { itt ->
        listAdapter.updateList(itt.map {
            when (it) {
                is AppInfo -> AppUiInfo.valueOf(it)
                is AppAction -> AppUiAction.valueOf(it)
            }
        })
    }

    private val itemClickListener = object : OnItemClickListener {
        override fun onLaunchClick(packageName: String) {
            val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
            startActivity(launchIntent)
        }

        override fun onShareClick(packageName: String) {
            shareApp(packageName)
        }

        override fun onSaveClick(packageName: String) {
            appDetailViewModel.saveAppApk(packageName)
        }

        override fun onMoreClick(packageName: String) {
            toast(R.string.more)
        }
    }

    private fun shareApp(packageName: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "application/apk"
        // Append file and send Intent

        val luanchbleIntent = packageManager.getLaunchIntentForPackage(packageName)
        val resloverInfo = packageManager.resolveActivity(luanchbleIntent, MATCH_DEFAULT_ONLY)

        val appFile = File(resloverInfo.activityInfo.applicationInfo.publicSourceDir)


        val appUri = FileProvider.getUriForFile(
            this,
            BuildConfig.APPLICATION_ID + ".provider",
            appFile
        )

        intent.putExtra(Intent.EXTRA_TITLE, packageName)
        intent.putExtra(Intent.EXTRA_STREAM, appUri)

        startActivity(Intent.createChooser(intent, "${getString(R.string.share)} $packageName"))
    }

    companion object {
        const val PACKAGE_NAME_KEY = "PACKAGE_NAME"
        const val ICON_URI_KEY = "ICON_URI"

        fun startWith(context: Context?, packageName: String, iconUri: Uri): Intent {
            val intent = Intent(context, AppDetailActivity::class.java)
            intent.putExtra(PACKAGE_NAME_KEY, packageName)
            intent.putExtra(ICON_URI_KEY, iconUri)
            return intent
        }
    }
}
