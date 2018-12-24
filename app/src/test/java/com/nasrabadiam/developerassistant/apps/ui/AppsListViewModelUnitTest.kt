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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nasrabadiam.developerassistant.apps.AppSummary
import com.nasrabadiam.developerassistant.apps.AppsDomain
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AppsListViewModelUnitTest {

    companion object {
        const val NAME = "app name"
        const val PACKAGE_NAME = "com.example"
        const val ICON_RES_ID = 164
    }

    private val appSummary = AppSummary(NAME, PACKAGE_NAME, ICON_RES_ID)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var appsDomain: AppsDomain
    @Mock
    lateinit var loadingObserver: Observer<Boolean>
    @Mock
    lateinit var appsListObserver: Observer<List<AppSummary>>


    private lateinit var vm: AppsListViewModel


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        vm = AppsListViewModel(appsDomain, Schedulers.newThread())

        vm.loading.observeForever(loadingObserver)
        vm.appsList.observeForever(appsListObserver)
    }

    @Test
    fun test_should_show_loading() {
        val observable = Observable.create<AppSummary> {it.onNext(appSummary)}

        `when`(appsDomain.getAllInstalledApps()).thenReturn(observable)
        vm.getAllInstalledApps()

        Mockito.verify(appsDomain, Mockito.times(1))
            .getAllInstalledApps()

        verify(loadingObserver).onChanged(true)
    }

    //TODO: test loading observer

    @Test
    fun test_should_show_items() {
        val observable = Observable.create<AppSummary> { it.onNext(appSummary) }

        `when`(appsDomain.getAllInstalledApps()).thenReturn(observable)
        vm.getAllInstalledApps()

        verify(appsListObserver).onChanged(listOf(appSummary))
    }

    @After
    fun tearDown() {
    }

}
