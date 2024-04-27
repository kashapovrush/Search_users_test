package com.kashapovrush.auth_feature.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.viewmodel.AuthViewModel
import com.kashapovrush.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}