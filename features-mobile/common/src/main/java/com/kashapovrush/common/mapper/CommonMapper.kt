package com.kashapovrush.common.mapper

import com.kashapovrush.common.entity.Repositories
import com.kashapovrush.network.dto.User
import com.kashapovrush.common.entity.UserInfo
import com.kashapovrush.network.dto.RepositoriesDto
import com.kashapovrush.network.dto.UserDto
import com.kashapovrush.network.dto.UserInfoDto




fun RepositoriesDto.toEntity(): Repositories = Repositories(
    id, name, description, updateDate, language, forksCount, defaultBranch, forks
)

fun List<RepositoriesDto>.toEntities(): List<Repositories> = map {
    it.toEntity()
}

fun UserInfoDto.toInfo(): UserInfo = UserInfo(id, login, name)