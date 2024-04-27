package com.kashapovrush.auth

import android.app.Application
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(application: Application) {

    fun getAuthRequest(): AuthorizationRequest {
        return AppAuth.getAuthRequest()
    }

    suspend fun performTokenRequest(authService: AuthorizationService, token: TokenRequest) {
        val tokens = AppAuth.performTokenRequestSuspend(authService, token)
        TokenStorage.accessToken = tokens.accessToken
        TokenStorage.refreshToken = tokens.refreshToken
        TokenStorage.idToken = tokens.idToken
    }

}