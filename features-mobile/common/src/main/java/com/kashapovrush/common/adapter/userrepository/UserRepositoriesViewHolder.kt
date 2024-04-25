package com.kashapovrush.common.adapter.userrepository

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserRepositoriesViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(com.kashapovrush.palette.R.id.name_repository)
    val description = view.findViewById<TextView>(com.kashapovrush.palette.R.id.description)
    val forks = view.findViewById<TextView>(com.kashapovrush.palette.R.id.forks)
    val stars = view.findViewById<TextView>(com.kashapovrush.palette.R.id.stars)
    val language = view.findViewById<TextView>(com.kashapovrush.palette.R.id.language)
    val date = view.findViewById<TextView>(com.kashapovrush.palette.R.id.date)
    val branch = view.findViewById<TextView>(com.kashapovrush.palette.R.id.branch)

}