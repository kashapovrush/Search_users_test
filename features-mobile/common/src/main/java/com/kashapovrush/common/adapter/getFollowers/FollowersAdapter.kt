package com.kashapovrush.common.adapter.getFollowers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.R
import com.kashapovrush.common.entity.Follower

class FollowersAdapter : ListAdapter<Follower, FollowerViewHolder>(DiffUtilFollowers()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_found_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val item = getItem(position)


    }
}