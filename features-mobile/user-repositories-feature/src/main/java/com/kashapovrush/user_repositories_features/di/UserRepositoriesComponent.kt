package com.kashapovrush.user_repositories_features.di

import android.content.Context
import com.kashapovrush.user_repositories_features.ui.UserRepositoriesFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UserRepositoriesModule::class, ViewModelModule::class])
interface UserRepositoriesComponent {

    fun inject(fragment: UserRepositoriesFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): UserRepositoriesComponent
    }
}