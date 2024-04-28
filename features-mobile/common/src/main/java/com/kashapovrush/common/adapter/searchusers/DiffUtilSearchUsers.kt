package com.kashapovrush.common.adapter.searchusers

import androidx.recyclerview.widget.DiffUtil

class DiffUtilSearchUsers : DiffUtil.ItemCallback<com.kashapovrush.network.dto.User>() {
    override fun areItemsTheSame(
        oldItem: com.kashapovrush.network.dto.User,
        newItem: com.kashapovrush.network.dto.User
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: com.kashapovrush.network.dto.User,
        newItem: com.kashapovrush.network.dto.User
    ): Boolean {
        return oldItem == newItem
    }

}