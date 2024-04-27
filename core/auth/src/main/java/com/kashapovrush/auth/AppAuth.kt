package com.kashapovrush.auth

import android.net.Uri
import androidx.core.net.toUri
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientAuthentication
import net.openid.appauth.ClientSecretPost
import net.openid.appauth.TokenRequest
import kotlin.coroutines.suspendCoroutine

object AppAuth {

    private val serviceConfiguration = AuthorizationServiceConfiguration(
        Uri.parse(AuthConfig.AUTH_URI),
        Uri.parse(AuthConfig.TOKEN_URI),
        null, // registration endpoint
        Uri.parse(AuthConfig.END_SESSION_URI)
    )

    fun getAuthRequest(): AuthorizationRequest {
        val redirectUri = AuthConfig.CALLBACK_URL.toUri()

        return AuthorizationRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID,
            AuthConfig.RESPONSE_TYPE,
            redirectUri
        ).setScope(AuthConfig.SCOPE)
            .build()
    }

    suspend fun performTokenRequestSuspend(authService: AuthorizationService, token: TokenRequest): TokensModel {
        return suspendCoroutine { continuation ->
            authService.performTokenRequest(token, getClientAuthentication()) { response, ex ->
                when {
                    response != null -> {
                        val tokens = TokensModel(
                            accessToken = response.accessToken.orEmpty(),
                            refreshToken = response.refreshToken.orEmpty(),
                            idToken = response.idToken.orEmpty()
                            )
                        continuation.resumeWith(Result.success(tokens))
                    }

                    ex != null -> {
                        continuation.resumeWith(Result.failure(ex))
                    }
                    else -> error("Error")

                }
            }
        }
    }

    private fun getClientAuthentication(): ClientAuthentication {
        return ClientSecretPost(AuthConfig.CLIENT_SECRET)
    }
}