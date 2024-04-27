package com.kashapovrush.auth_user_feature.di

import android.app.Application
import com.kashapovrush.auth_user_feature.ui.AuthUserFragment
import com.kashapovrush.utils.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [AuthUserModule::class, ViewModelModule::class])
interface AuthUserComponent {

    fun inject(fragment: AuthUserFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AuthUserComponent
    }
}