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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    val result: MutableLiveData<List<AppSummary>> = MutableLiveData()

    lateinit var allAppsList: List<AppSummary>

    fun searchForQuery(query: String) {
        result.postValue(allAppsList.filter {
            it.name.contains(query) || it.packageName.contains(query)
        })
    }

}
