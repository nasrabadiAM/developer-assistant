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

package com.nasrabadiam.developerassistant.apps.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasrabadiam.developerassistant.apps.AppSummary
import com.nasrabadiam.developerassistant.apps.AppsDomain
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class AppsListViewModel constructor(
    private val appsDomain: AppsDomain,
    private val mainScheduler: Scheduler
) : ViewModel() {

    val appsList: MutableLiveData<List<AppSummary>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    private val appsSummaryList = ArrayList<AppSummary>()
    private val disposables = CompositeDisposable()

    fun getAllInstalledApps() {
        loading.postValue(true)
        val getApps = appsDomain.getAllInstalledApps()
            .subscribeOn(Schedulers.io())
            .observeOn(mainScheduler)
            .subscribeWith(appsListObserver)
        disposables.addAll(getApps)
    }

    private val appsListObserver =
        object : DisposableObserver<AppSummary>() {
            override fun onComplete() {
                loading.postValue(false)
            }

            override fun onError(e: Throwable) {
                loading.postValue(false)
            }

            override fun onNext(t: AppSummary) {
                appsSummaryList.add(t)
                appsList.postValue(appsSummaryList)
            }
        }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}

class ViewModelFactory(private val domain: AppsDomain, private val mainScheduler: Scheduler) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AppsListViewModel::class.java)) {
            AppsListViewModel(this.domain, mainScheduler) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
