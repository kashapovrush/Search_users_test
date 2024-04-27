package com.kashapovrush.search_users_features.di

import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import com.kashapovrush.utils.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object SearchUsersModule {

    @ApplicationScope
    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}