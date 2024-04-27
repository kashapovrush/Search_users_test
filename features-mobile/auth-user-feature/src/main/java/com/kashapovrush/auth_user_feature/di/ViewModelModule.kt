package com.kashapovrush.auth_user_feature.di

import androidx.lifecycle.ViewModel
import com.kashapovrush.common.viewmodel.AuthUserViewModel
import com.kashapovrush.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(AuthUserViewModel::class)
    fun bindsAuthUserViewModel(viewModel: AuthUserViewModel): ViewModel
}