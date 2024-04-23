package com.kashapovrush.network.api

import com.kashapovrush.network.dto.ResponseUsers
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): ResponseUsers
}