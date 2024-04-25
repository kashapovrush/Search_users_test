package com.kashapovrush.common.mapper

import com.kashapovrush.common.entity.Repositories
import com.kashapovrush.common.entity.User
import com.kashapovrush.network.dto.RepositoriesDto
import com.kashapovrush.network.dto.UserDto


fun UserDto.toUserEntity(): User = User(
    avatarUrl = avatar_url,
    followersUrl = followers_url,
    id = id,
    login = login,
    reposUrl = repos_url
)

fun List<UserDto>.toUsersEntities(): List<User> = map { it.toUserEntity() }

fun RepositoriesDto.toEntity(): Repositories = Repositories(
    id, name, description, updateDate, language, forksCount, defaultBranch, forks
)

fun List<RepositoriesDto>.toEntities(): List<Repositories> = map {
    it.toEntity()
}