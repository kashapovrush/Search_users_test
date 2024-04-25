package com.kashapovrush.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kashapovrush.common.entity.Repositories
import com.kashapovrush.common.mapper.toEntities
import com.kashapovrush.network.api.ApiService
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoriesViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {

    private val _repositories = MutableLiveData<List<Repositories>>()
    val repositories: LiveData<List<Repositories>> = _repositories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    suspend fun getUserRepositories(login: String) = flow {
        _loading.postValue(true)
        emit(apiService.getUserRepositories(login))
    }.map {
        it.toEntities()
    }.catch {
        _loading.postValue(false)
        _error.postValue(it.message)
    }.collect {
        _loading.postValue(false)
        _repositories.postValue(it)
    }
}