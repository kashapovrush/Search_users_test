package com.kashapovrush.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kashapovrush.network.api.SearchUsersPageSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class SearchUsersViewModel @Inject constructor(
    private val pageSource: SearchUsersPageSource.Factory
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    fun getUser(query: String) = Pager(
        config = PagingConfig(5),
    ) {
        pageSource.create(query)
    }.flow.catch {
        _error.postValue(it.message)
    }.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

}


