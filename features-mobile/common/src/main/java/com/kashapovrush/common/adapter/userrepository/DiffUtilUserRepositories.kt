package com.kashapovrush.common.adapter.userrepository

import androidx.recyclerview.widget.DiffUtil
import com.kashapovrush.common.entity.Repositories

class DiffUtilUserRepositories: DiffUtil.ItemCallback<Repositories>() {
    override fun areItemsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
        return oldItem == newItem
    }
}