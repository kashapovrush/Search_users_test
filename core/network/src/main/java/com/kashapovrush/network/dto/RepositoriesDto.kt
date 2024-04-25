package com.kashapovrush.network.dto

import com.google.gson.annotations.SerializedName


/*
При клике на элемент списка переходим  на экран на котором отображается список репозиториев этого
пользователя. Элемент списка должен состоять из
, звезд.
 */
data class RepositoriesDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("pushed_at") val updateDate: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("forks_count") val forksCount: Int?,
    @SerializedName("default_branch") val defaultBranch: String?,
    @SerializedName("forks") val forks: Int?
)
