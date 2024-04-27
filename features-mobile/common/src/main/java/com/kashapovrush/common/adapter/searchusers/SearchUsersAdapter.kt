package com.kashapovrush.common.adapter.searchusers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import com.kashapovrush.network.dto.User

class SearchUsersAdapter(val context: Context): PagingDataAdapter<User, SearchViewViewHolder>(
    DiffUtilSearchUsers()
) {

    var onClickListenerToItem: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewViewHolder {
        return SearchViewViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.kashapovrush.palette.R.layout.item_found_user, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewViewHolder, position: Int) {
        val user = getItem(position)

        Glide.with(context).load(user?.avatarUrl).into(holder.imageUser)
        holder.username.text = user?.login
        holder.followers.text = user?.countFollowers.toString()

        holder.view.setOnClickListener {
            onClickListenerToItem?.invoke(user?.login ?: "")
        }
    }


}