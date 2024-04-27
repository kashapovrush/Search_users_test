package com.kashapovrush.common.adapter.userrepository

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import com.kashapovrush.common.entity.Repositories
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserRepositoriesAdapter : ListAdapter<Repositories, UserRepositoriesViewHolder>(DiffUtilUserRepositories()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoriesViewHolder {
        return UserRepositoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(com.kashapovrush.palette.R.layout.item_user_repositories, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UserRepositoriesViewHolder, position: Int) {
        val item = getItem(position)

        with(holder) {
            name.text = item.name
            description.text = item.description
            forks.text= item.forks.toString()
            stars.text = item.forksCount.toString()
            date.text = converterDate(item.updateDate ?: "")
            branch.text = item.defaultBranch
            language.text = item.language
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun converterDate(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateTime = LocalDateTime.parse(date, formatter)
        val newFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")
        val formattedDate = dateTime.format(newFormatter)
        return formattedDate
    }


}