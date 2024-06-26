package com.kashapovrush.auth_feature.di

import android.app.Application
import com.kashapovrush.utils.ApplicationScope
import dagger.Module
import dagger.Provides
import net.openid.appauth.AuthorizationService

@Module
object AuthModule {

    @ApplicationScope
    @Provides
    fun provideAuthorizationService(application: Application): AuthorizationService {
        return AuthorizationService(application)
    }
}