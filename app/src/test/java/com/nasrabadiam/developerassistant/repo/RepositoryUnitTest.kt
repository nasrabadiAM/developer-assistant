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

package com.nasrabadiam.developerassistant.repo

import com.nasrabadiam.developerassistant.apps.Repository
import com.nasrabadiam.developerassistant.repo.android.AndroidRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepositoryUnitTest {

    @Mock
    lateinit var androidRepository: AndroidRepository

    private lateinit var repository: Repository

    private val observable = Observable.create<AppSummaryEntity> {}

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RepositoryImpl(androidRepository)
    }

    @Test
    fun test_get_installed_apps() {
        `when`(androidRepository.getInstalledApps()).thenReturn(observable)
        val result = repository.getInstalledApps()
        Assert.assertEquals(result, observable)
        Mockito.verify(androidRepository, Mockito.times(1))
            .getInstalledApps()
    }
}
