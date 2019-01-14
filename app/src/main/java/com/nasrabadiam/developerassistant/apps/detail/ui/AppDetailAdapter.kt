package com.nasrabadiam.developerassistant.apps.detail.ui

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

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nasrabadiam.developerassistant.R
import com.nasrabadiam.developerassistant.inflate
import com.nasrabadiam.developerassistant.loadImage

class AppDetailAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<AppDetailListItems> = ArrayList()

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.inflate(viewType)
        return when (viewType) {
            R.layout.app_action_item -> {
                val holder = ActionItemViewHolder(view)
                holder.apply {
                    launchParent.setOnClickListener {
                        val packageName = (items[holder.adapterPosition] as AppUiAction).packageName
                        onItemClickListener?.onLaunchClick(packageName)
                    }
                    shareParent.setOnClickListener {
                        val packageName = (items[holder.adapterPosition] as AppUiAction).packageName
                        onItemClickListener?.onShareClick(packageName)
                    }
                    saveParent.setOnClickListener {
                        val packageName = (items[holder.adapterPosition] as AppUiAction).packageName
                        onItemClickListener?.onSaveClick(packageName)
                    }
                    moreParent.setOnClickListener {
                        val packageName = (items[holder.adapterPosition] as AppUiAction).packageName
                        onItemClickListener?.onMoreClick(packageName)
                    }
                }
                holder
            }
            R.layout.app_info_item -> {
                InfoItemViewHolder(view)
            }
            else -> InfoItemViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            items[position] is AppUiAction -> R.layout.app_action_item
            items[position] is AppUiInfo -> R.layout.app_info_item
            else -> R.layout.app_info_item
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ActionItemViewHolder -> {
                holder.onBind(items[position] as AppUiAction)
            }
            is InfoItemViewHolder -> {
                holder.onBind(items[position] as AppUiInfo)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (holder) {
                is ActionItemViewHolder -> {
                    val bundle = payloads[0] as Bundle
                    for (key in bundle.keySet()) {
                        val actionItem = items[position] as AppUiAction
                        when (key) {
                            "image" -> {
                                holder.appImage.loadImage(actionItem.iconUri)
                            }
                        }
                    }
                }
                is InfoItemViewHolder -> {
                    val bundle = payloads[0] as Bundle
                    for (key in bundle.keySet()) {
                        val appInfo = items[position] as AppUiInfo
                        when (key) {
                            "title" -> {
                                holder.title.text = appInfo.title
                            }
                            "data" -> {
                                holder.data.text = appInfo.info
                            }
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newData: List<AppDetailListItems>) {
        val diffCallback = AppDetailDiffUtil(this.items, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback, false)

        this.items.clear()
        this.items.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItems(): List<AppDetailListItems> {
        return items
    }

}

interface OnItemClickListener {
    fun onLaunchClick(packageName: String)
    fun onShareClick(packageName: String)
    fun onSaveClick(packageName: String)
    fun onMoreClick(packageName: String)
}

class ActionItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val launchParent: View = view.findViewById(R.id.launch_parent)
    val shareParent: View = view.findViewById(R.id.share_parent)
    val saveParent: View = view.findViewById(R.id.save_parent)
    val moreParent: View = view.findViewById(R.id.more_parent)
    val appImage: ImageView = view.findViewById(R.id.app_image)

    fun onBind(appItem: AppUiAction) {
        appImage.loadImage(appItem.iconUri)
    }
}

class InfoItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById(R.id.title)
    val data: TextView = view.findViewById(R.id.data)

    fun onBind(appInfo: AppUiInfo) {
        title.text = appInfo.title
        data.text = appInfo.info
    }
}

class AppDetailDiffUtil(
    private val oldItems: List<AppDetailListItems>,
    private val newItems: List<AppDetailListItems>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPostion: Int): Boolean {
        return when {
            (oldItems[oldItemPosition] is AppUiAction && newItems[newItemPostion] is AppUiAction) ->
                (oldItems[oldItemPosition] as AppUiAction) == newItems[newItemPostion] as AppUiAction

            (oldItems[oldItemPosition] is AppUiInfo && newItems[newItemPostion] is AppUiInfo) ->
                (oldItems[oldItemPosition] as AppUiInfo) == newItems[newItemPostion] as AppUiInfo

            else -> false
        }
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPostion: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPostion]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Bundle {

        val newItem = newItems[newItemPosition]
        val oldItem = oldItems[oldItemPosition]
        val diff = Bundle()

        when (newItem) {
            is AppUiAction -> {
                val appAction = oldItem as AppUiAction
                if (newItem.iconUri != appAction.iconUri) {
                    diff.putParcelable("image", newItem.iconUri)
                }
            }
            is AppUiInfo -> {
                val appInfo = oldItem as AppUiInfo
                if (newItem.title != appInfo.title) {
                    diff.putString("title", newItem.title)
                }
                if (newItem.info != appInfo.info) {
                    diff.putString("data", newItem.info)
                }
            }
        }
        return if (diff.size() == 0) {
            Bundle()
        } else diff
    }
}

