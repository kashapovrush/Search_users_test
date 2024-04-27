package com.kashapovrush.user_repositories_features.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.viewmodel.UserRepositoriesViewModel
import com.kashapovrush.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(UserRepositoriesViewModel::class)
    fun bindHeadlinesViewModel(viewModel: UserRepositoriesViewModel): ViewModel
}