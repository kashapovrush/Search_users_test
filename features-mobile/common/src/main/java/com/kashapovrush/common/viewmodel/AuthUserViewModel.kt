package com.kashapovrush.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashapovrush.common.entity.UserInfo
import com.kashapovrush.common.mapper.toInfo
import com.kashapovrush.network.api.ApiService
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


    fun getUserInfo() {
        viewModelScope.launch {
            _loading.value = true
            runCatching {
                apiService.getUserProfile()
            }.map {
                it.toInfo()
            }.onFailure {
                _loading.value = false
                _error.value = it.message
            }.onSuccess {
                _loading.value = false
                _user.value = it
            }
        }

    }
}