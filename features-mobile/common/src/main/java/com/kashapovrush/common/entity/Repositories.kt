package com.kashapovrush.common.entity

data class Repositories(
    val id: Int,
    val name: String?,
    val description: String?,
    val updateDate: String?,
    val language: String?,
    val forksCount: Int?,
    val defaultBranch: String?,
    val forks: Int?
)
