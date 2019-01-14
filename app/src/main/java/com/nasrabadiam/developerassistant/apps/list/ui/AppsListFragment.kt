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

package com.nasrabadiam.developerassistant.apps.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.apps.AppSummary
import com.nasrabadiam.developerassistant.apps.AppsDomainImpl
import com.nasrabadiam.developerassistant.apps.detail.ui.AppDetailActivity
import com.nasrabadiam.developerassistant.apps.list.SearchViewModel
import com.nasrabadiam.developerassistant.invisible
import com.nasrabadiam.developerassistant.repo.RepositoryImpl
import com.nasrabadiam.developerassistant.repo.android.AndroidRepositoryImpl
import com.nasrabadiam.developerassistant.visible
import io.reactivex.android.schedulers.AndroidSchedulers
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

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val domain = AppsDomainImpl(
            RepositoryImpl(
                AndroidRepositoryImpl(context!!)
            )
        )

        viewModelFactory =
                ViewModelFactory(domain, AndroidSchedulers.mainThread())


        appsViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AppsListViewModel::class.java)

        searchViewModel = activity?.run {
            ViewModelProviders.of(this)
                .get(SearchViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        appsViewModel.apply {
            appsList.observe(this@AppsListFragment, Observer { it ->
                (rootView.apps_recycler_view.adapter as AppsListAdapter).updateList(it.map {
                    AppListItem.valueOf(
                        it
                    )
                })
                setupSearch(it)
            })
            loading.observe(this@AppsListFragment, Observer {
                if (it) {
                    rootView.progress_bar.visible()
                } else {
                    rootView.progress_bar.invisible()
                }
            })
        }
        searchViewModel.result.observe(this, Observer { it ->
            (rootView.apps_recycler_view.adapter as AppsListAdapter).updateList(it.map {
                AppListItem.valueOf(
                    it
                )
            })
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_apps, container, false)
        rootView.apps_recycler_view.apply {
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.apps_columns))
            setHasFixedSize(true)
            adapter = AppsListAdapter()
            (adapter as AppsListAdapter).onItemClickListener =
                    object : OnItemClickListener {
                        override fun onClick(item: AppListItem) {
                            showAppDetail(item)
                        }
                    }
        }
        return rootView
    }

    private fun showAppDetail(item: AppListItem) {
        startActivity(AppDetailActivity.startWith(this.context, item.packageName, item.iconUri))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) {
            appsViewModel.getAllInstalledApps()
        }
    }

    private fun setupSearch(list: List<AppSummary>) {
        searchViewModel.allAppsList = list
    }
}


