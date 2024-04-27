package com.kashapovrush.auth_feature.di

import android.app.Application
import com.kashapovrush.auth_feature.ui.AuthFragment
import com.kashapovrush.utils.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ViewModelModule::class, AuthModule::class])
interface AuthComponent {

    fun inject(fragment: AuthFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AuthComponent
    }
}