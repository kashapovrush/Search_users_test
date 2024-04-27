package com.kashapovrush.auth_user_feature.di

import com.kashapovrush.network.api.ApiFactory
import com.kashapovrush.network.api.ApiService
import com.kashapovrush.utils.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object AuthUserModule {

    @ApplicationScope
    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }
}