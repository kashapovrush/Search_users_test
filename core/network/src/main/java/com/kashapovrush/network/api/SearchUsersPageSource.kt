package com.kashapovrush.network.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kashapovrush.network.dto.User
import com.kashapovrush.network.dto.toUserEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class SearchUsersPageSource @AssistedInject constructor(
    private val apiService: ApiService,
    @Assisted("query") private val query: String
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(10)

        val response = apiService.searchUsers(query = query, perPage = pageSize, page = page)

        if (response.isSuccessful) {
            val users = checkNotNull(response.body()?.users?.map { userDto ->

                val responseFollowerForCountUser = apiService.getFollowers(userDto.login)
                if (responseFollowerForCountUser.isSuccessful) {
                    userDto.toUserEntity(responseFollowerForCountUser.body()?.size ?: 0)

                } else {
                    userDto.toUserEntity(0)
                }
            })

            val nextKey = if (users.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            return LoadResult.Page(users, prevKey, nextKey)
        } else {
            return LoadResult.Error(HttpException(response))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("query") query: String): SearchUsersPageSource
    }
}
