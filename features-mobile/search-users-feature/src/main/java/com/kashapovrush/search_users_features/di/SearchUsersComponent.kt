package com.kashapovrush.search_users_features.di

import android.content.Context
import com.kashapovrush.search_users_features.ui.SearchUsersFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [SearchUsersModule::class, ViewModelModule::class])
interface SearchUsersComponent {

    fun inject(fragment: SearchUsersFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): SearchUsersComponent
    }
}