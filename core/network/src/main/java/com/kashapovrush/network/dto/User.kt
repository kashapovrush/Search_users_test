package com.kashapovrush.network.dto

data class User(
    val avatarUrl: String? = "",
    val id: Int? = 0,
    val login: String? = "",
    val reposUrl: String? = "",
    val followers: Int = 0,
    val countFollowers: Int = 0
)