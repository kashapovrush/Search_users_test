package com.kashapovrush.network.api


import com.kashapovrush.network.dto.FollowerDto


import com.kashapovrush.network.dto.FollowerDto

import com.kashapovrush.network.dto.ResponseUsers
import com.kashapovrush.network.dto.UserDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

import com.kashapovrush.network.dto.ResponseUsers
import retrofit2.http.GET


import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ResponseUsers


    @GET("users/{name}/followers")
    suspend fun getFollowers(
        @Path("name") name: String
    ): List<UserDto>


    @GET("users/{login}/repos")
    suspend fun getUserRepositories(
        @Path("login") login: String
    ): List<RepositoriesDto>


}