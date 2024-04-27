package com.kashapovrush.common.viewmodel

import android.content.Intent
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.auth.AuthRepository
import com.kashapovrush.common.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenRequest
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authService: AuthorizationService
) : ViewModel() {

    private val openAuthPageEventChannel = Channel<Intent>(Channel.BUFFERED)
    private val toastEventChannel = Channel<Int>(Channel.BUFFERED)
    private val authSuccessEventChannel = Channel<Unit>(Channel.BUFFERED)

    val openAuthPageFlow: Flow<Intent>
        get() = openAuthPageEventChannel.receiveAsFlow()

    val authSuccessFlow: Flow<Unit>
        get() = authSuccessEventChannel.receiveAsFlow()

    val toastFlow: Flow<Int>
        get() = toastEventChannel.receiveAsFlow()

    fun openLoginPage() {
        val customTabsIntent = CustomTabsIntent.Builder().build()

        val authRequest = authRepository.getAuthRequest()

        val openAuthPageIntent =
            authService.getAuthorizationRequestIntent(authRequest, customTabsIntent)

        openAuthPageEventChannel.trySendBlocking(openAuthPageIntent)
    }

    override fun onCleared() {
        super.onCleared()
        authService.dispose()
    }

    fun onAuthCodedFailed(exception: AuthorizationException) {
        toastEventChannel.trySendBlocking(R.string.error)
    }

    fun onAuthCodeReceived(token: TokenRequest) {
        viewModelScope.launch {
            runCatching {
                authRepository.performTokenRequest(authService, token)
            }.onSuccess {
                authSuccessEventChannel.send(Unit)
            }.onFailure {
                toastEventChannel.send(0)
            }
        }
    }
}