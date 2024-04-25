package com.kashapovrush.common.adapter.searchusers

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.entity.User

class DiffUtilSearchUsers: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}