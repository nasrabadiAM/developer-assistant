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
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.loadImage

class AppsListAdapter :
    RecyclerView.Adapter<AppItemViewHolder>() {

    private var items: ArrayList<AppListItem> = ArrayList()

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        val holder = AppItemViewHolder(view)
        holder.parent.setOnClickListener {
            onItemClickListener?.onClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.app_list_item
    }

    override fun onBindViewHolder(holder: AppItemViewHolder, position: Int) {
        holder.onBind(items[holder.adapterPosition])
    }

    override fun onBindViewHolder(
        holder: AppItemViewHolder,
        position: Int, payloads: List<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val bundle = payloads[0] as Bundle
            for (key in bundle.keySet()) {
                val app = items[holder.adapterPosition]
                when (key) {
                    "name" -> {
                        holder.title.text = app.name
                    }
                    "packageName" -> {
                        //do nothing
                    }
                    "iconUri" -> {
                        holder.image.loadImage(app.iconUri)
                    }
                    else -> {
                        //do nothing
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newData: List<AppListItem>) {
        val diffCallback = BlogPostDiffUtil(this.items, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)

        this.items.clear()
        this.items.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItems(): List<AppListItem> {
        return items
    }
}

interface OnItemClickListener {
    fun onClick(item: AppListItem)
}

class AppItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(R.id.title)
    val image: ImageView = view.findViewById(R.id.image)
    val parent: View = view.findViewById(R.id.parent)

    fun onBind(
        app: AppListItem
    ) {
        title.text = app.name
        image.loadImage(app.iconUri)
    }
}


class BlogPostDiffUtil(
    private val oldItems: List<AppListItem>,
    private val newItems: List<AppListItem>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPostion: Int): Boolean {
        return oldItems[oldItemPosition].name == newItems[newItemPostion].name &&
                oldItems[oldItemPosition].packageName == newItems[newItemPostion].packageName &&
                oldItems[oldItemPosition].iconUri == newItems[newItemPostion].iconUri

    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPostion: Int): Boolean {
        return oldItems[oldItemPosition].compareTo(newItems[newItemPostion]) == 0
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

        val newItem = newItems[newItemPosition]
        val oldItem = oldItems[oldItemPosition]

        val diff = Bundle()
        if (newItem.name != oldItem.name) {
            diff.putString("name", newItem.name)
        }
        if (newItem.packageName != oldItem.packageName) {
            diff.putString("packageName", newItem.packageName)
        }
        if (newItem.iconUri != oldItem.iconUri) {
            diff.putString("iconUri", newItem.iconUri.toString())
        }
        return if (diff.size() == 0) {
            null
        } else diff
    }
}

