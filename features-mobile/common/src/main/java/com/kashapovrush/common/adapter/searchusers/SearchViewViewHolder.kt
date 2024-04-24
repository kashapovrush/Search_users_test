package com.kashapovrush.common.adapter.searchusers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kashapovrush.common.R

class SearchViewViewHolder(val view: View): ViewHolder(view) {

    val imageUser = view.findViewById<ImageView>(R.id.avatar_user)
    val username = view.findViewById<TextView>(R.id.username)
    val followers = view.findViewById<TextView>(R.id.followers)
}