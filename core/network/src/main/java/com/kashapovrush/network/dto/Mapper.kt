package com.kashapovrush.network.dto

fun UserDto.toUserEntity(count: Int): User = User(
    avatarUrl = avatar_url,
    countFollowers = count,
    id = id,
    login = login,
    reposUrl = repos_url
)

//fun List<UserDto>.toUsersEntities(): List<User> = map { it.toUserEntity() }