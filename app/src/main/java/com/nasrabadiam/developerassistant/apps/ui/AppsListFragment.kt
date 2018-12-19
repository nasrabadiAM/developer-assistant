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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.apps.AppDomainImpl
import com.nasrabadiam.developerassistant.repo.RepositoryImpl
import com.nasrabadiam.developerassistant.repo.android.AndroidRepositoryImpl
import kotlinx.android.synthetic.main.fragment_apps.view.*

class AppsListFragment : Fragment() {

    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var appsViewModel: AppsListViewModel
    private lateinit var rootView: View

    companion object {
        fun newInstance(): AppsListFragment {
            return AppsListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory =
                ViewModelFactory(
                    AppDomainImpl(
                        RepositoryImpl(
                            AndroidRepositoryImpl(context!!)
                        )
                    )
                )


        appsViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AppsListViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_apps, container, false)
        rootView.recycler_view.layoutManager = GridLayoutManager(context, 3)
        rootView.recycler_view.adapter = AppsListAdapter()
        (rootView.recycler_view.adapter as AppsListAdapter).onItemClickListener =
                object : OnItemClickListener {
                    override fun onClick(item: AppListItem) {
                        showAppDetail(item)
                    }
                }

        rootView.progress_bar.isIndeterminate = true

        return rootView
    }

    private fun showAppDetail(item: AppListItem) {
        //TODO: show app details
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appsViewModel.getAllInstalledApps()
        appsViewModel.appsList.observe(this, Observer {
            (rootView.recycler_view.adapter as AppsListAdapter).updateList(it)
        })
        appsViewModel.loading.observe(this, Observer {
            if (it) {
                rootView.progress_bar.visibility = View.VISIBLE
            } else {
                rootView.progress_bar.visibility = View.INVISIBLE
            }
        })
    }

}


