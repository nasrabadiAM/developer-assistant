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

package com.nasrabadiam.developerassistant.apps

import com.nasrabadiam.developerassistant.repo.AppSummaryEntity
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AppsDomainImplUnitTest {

    lateinit var appsDomain: AppsDomain

    @Mock
    lateinit var repository: Repository

    private val appSummaryEntity = AppSummaryEntity("", "", 12)

    private val observable =
        Observable.just(appSummaryEntity)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        appsDomain = AppsDomainImpl(repository)
    }

    @Test
    fun test_get_all_installed_apps() {
        `when`(repository.getInstalledApps()).thenReturn(observable)
        val result = appsDomain.getAllInstalledApps()

        verify(repository, Mockito.times(1))
            .getInstalledApps()

        result.subscribe {
            Assert.assertEquals(it, appSummaryEntity.getDomainModel())
        }
    }
}
