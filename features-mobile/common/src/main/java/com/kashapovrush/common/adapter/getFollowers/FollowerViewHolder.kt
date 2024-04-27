package com.kashapovrush.common.adapter.getFollowers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.common.R

class FollowerViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val followers = view.findViewById<TextView>(R.id.followers)
}