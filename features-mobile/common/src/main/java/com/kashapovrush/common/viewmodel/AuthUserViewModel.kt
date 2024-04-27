package com.kashapovrush.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.common.entity.UserInfo
import com.kashapovrush.common.mapper.toInfo
import com.kashapovrush.network.api.ApiService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthUserViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _user = MutableLiveData<UserInfo>()
    val user: LiveData<UserInfo> = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val success = Channel<UserInfo>(Channel.BUFFERED)

    val authSuccessFlow: Flow<UserInfo>
        get() = success.receiveAsFlow()


    suspend fun getUserInfo() = flow {
        emit(apiService.getUserProfile())
    }.map {
        it.toInfo()
    }.catch {
        viewModelScope.launch {
            _error.value = it.message
        }
    }.collect {
        viewModelScope.launch {
            success.send(it)
        }
    }
}
