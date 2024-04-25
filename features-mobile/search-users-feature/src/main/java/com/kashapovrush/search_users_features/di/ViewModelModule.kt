package com.kashapovrush.search_users_features.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.viewmodel.SearchUsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModuleKey(SearchUsersViewModel::class)
    fun bindHeadlinesViewModel(viewModel: SearchUsersViewModel): ViewModel
}