package com.kashapovrush.search_users_features.di

import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import dagger.Module
import dagger.Provides

@Module
object SearchUsersModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}