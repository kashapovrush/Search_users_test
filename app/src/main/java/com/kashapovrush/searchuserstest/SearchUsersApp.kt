package com.kashapovrush.searchuserstest

import android.app.Application
import com.kashapovrush.auth_feature.di.AuthComponent
import com.kashapovrush.auth_feature.di.AuthComponentProvider
import com.kashapovrush.auth_feature.di.DaggerAuthComponent
import com.kashapovrush.auth_user_feature.di.AuthUserComponent
import com.kashapovrush.auth_user_feature.di.AuthUserComponentProvider
import com.kashapovrush.auth_user_feature.di.DaggerAuthUserComponent
import com.kashapovrush.search_users_features.di.DaggerSearchUsersComponent
import com.kashapovrush.search_users_features.di.SearchUsersComponent
import com.kashapovrush.search_users_features.di.SearchUsersComponentProvider
import com.kashapovrush.user_repositories_features.di.DaggerUserRepositoriesComponent
import com.kashapovrush.user_repositories_features.di.UserRepositoriesComponent
import com.kashapovrush.user_repositories_features.di.UserRepositoriesComponentProvider

class SearchUsersApp : Application(), SearchUsersComponentProvider,
    UserRepositoriesComponentProvider, AuthComponentProvider, AuthUserComponentProvider {
    override fun getSearchUsersComponent(): SearchUsersComponent {
        return DaggerSearchUsersComponent.factory().create(this)
    }

    override fun getUserRepositoriesComponent(): UserRepositoriesComponent {
        return DaggerUserRepositoriesComponent.factory().create(this)
    }

    override fun getAuthComponent(): AuthComponent {
        return DaggerAuthComponent.factory().create(this)
    }

    override fun getAuthUserComponent(): AuthUserComponent {
        return DaggerAuthUserComponent.factory().create(this)
    }
}