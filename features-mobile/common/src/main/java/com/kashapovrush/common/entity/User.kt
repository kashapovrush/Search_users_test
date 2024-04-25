package com.kashapovrush.common.entity

data class User(
    val avatarUrl: String? = "",
    val followersUrl: String? = "",
    val id: Int? = 0,
    val login: String? = "",
    val reposUrl: String? = "",
    val followers: Int = 0
)
