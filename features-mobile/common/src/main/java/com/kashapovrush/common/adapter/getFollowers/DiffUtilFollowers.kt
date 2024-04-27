package com.kashapovrush.common.adapter.getFollowers

import androidx.loader.content.Loader.ForceLoadContentObserver
import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.entity.Follower

class DiffUtilFollowers: DiffUtil.ItemCallback<Follower>() {
    override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem == newItem
    }
}