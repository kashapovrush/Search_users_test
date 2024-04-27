package com.kashapovrush.user_repositories_features.di

import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import dagger.Module
import dagger.Provides

@Module
object UserRepositoriesModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}