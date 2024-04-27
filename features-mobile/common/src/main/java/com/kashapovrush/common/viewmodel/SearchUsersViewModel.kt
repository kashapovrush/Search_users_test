package com.kashapovrush.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.kashapovrush.common.entity.User
import com.kashapovrush.common.mapper.toEntities
import com.kashapovrush.network.api.ApiService
import com.kashapovrush.network.dto.ResponseUsers
import com.kashapovrush.network.dto.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import com.kashapovrush.common.entity.User
import com.kashapovrush.common.mapper.toUsersEntities
import com.kashapovrush.network.api.ApiService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

import javax.inject.Inject

class SearchUsersViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _users = MutableLiveData<User>()
    val users: LiveData<User> = _users

    private val _result = MutableLiveData<List<User>>()
    val result: LiveData<List<User>> = _result

    private val _followers = MutableLiveData<Int>()
    val followers: LiveData<Int> = _followers

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading



    suspend fun getUsers(query: String) = flow {
        _loading.postValue(true)
        emit(apiService.searchUsers(query))
    }.map {

        it.users.toEntities()

        it.users.toUsersEntities()

    }.onEach { list ->
        list.forEach {
            flow {
                emit(apiService.getFollowers(it.login ?: ""))
            }.map {
                it.size
            }.collect {
                _followers.postValue(it)
            }
        }
    }.collect {
        _loading.postValue(false)
        _result.postValue(it)
    }
}


