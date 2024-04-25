package com.kashapovrush.user_repositories_features.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.viewmodel.SearchUsersViewModel
import com.kashapovrush.common.viewmodel.UserRepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModuleKey(UserRepositoriesViewModel::class)
    fun bindHeadlinesViewModel(viewModel: UserRepositoriesViewModel): ViewModel
}