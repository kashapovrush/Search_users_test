package com.kashapovrush.common.mapper

import com.kashapovrush.common.entity.User
import com.kashapovrush.network.dto.UserDto

fun UserDto.toEntity(): User = User(
    avatarUrl = avatar_url,
    followersUrl = followers_url,
    id = id,
    login = login,
    reposUrl = repos_url
)

fun List<UserDto>.toEntities(): List<User> = map { it.toEntity() }