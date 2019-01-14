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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasrabadiam.developerassistant.apps.AppsDomain
import com.nasrabadiam.developerassistant.apps.detail.AppAction
import com.nasrabadiam.developerassistant.apps.detail.AppDetail
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class AppDetailViewModel constructor(
    private val appsDomain: AppsDomain,
    private val mainScheduler: Scheduler
) : ViewModel() {

    val appDetails = MutableLiveData<List<AppDetail>>()
    private val appDetailList = ArrayList<AppDetail>()

    private val disposables = CompositeDisposable()

    fun getAppDetail(packageName: String, iconUriString: String) {
        appDetailList.add(AppAction(packageName, iconUriString))
        appDetails.postValue(appDetailList)

        val getAppDetail = appsDomain.getAppDetails(packageName)
            .subscribeOn(Schedulers.io())
            .observeOn(mainScheduler)
            .subscribeWith(appDetailObserver)
        disposables.addAll(getAppDetail)
    }

    private val appDetailObserver =
        object : DisposableObserver<AppDetail>() {
            override fun onComplete() {}
            override fun onError(e: Throwable) {}

            override fun onNext(t: AppDetail) {
                appDetailList.add(t)
                appDetails.postValue(appDetailList)
            }
        }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    fun saveAppApk(packageName: String) {
        appsDomain.saveApk(packageName)
    }
}

class ViewModelFactory(private val domain: AppsDomain, private val mainScheduler: Scheduler) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AppDetailViewModel::class.java)) {
            AppDetailViewModel(this.domain, mainScheduler) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
