package com.kashapovrush.network.api

import androidx.annotation.IntRange
import androidx.lifecycle.LiveData
import com.kashapovrush.network.dto.FollowerDto
import com.kashapovrush.network.dto.RepositoriesDto
import com.kashapovrush.network.dto.ResponseUsers
import com.kashapovrush.network.dto.UserDto
import com.kashapovrush.network.dto.UserInfoDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") @IntRange(from = 1, to = 10) perPage: Int = 10,
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): Response<ResponseUsers>

    @GET("users/{name}/followers")
    suspend fun getFollowers(
        @Path("name") name: String
    ): Response<List<UserDto>>

    @GET("users/{login}/repos")
    suspend fun getUserRepositories(
        @Path("login") login: String
    ): List<RepositoriesDto>

    @GET("user")
    suspend fun getUserProfile(): UserInfoDto
}