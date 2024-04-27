package com.kashapovrush.searchuserstest

import android.app.Application
import com.kashapovrush.search_users_features.di.DaggerSearchUsersComponent
import com.kashapovrush.search_users_features.di.SearchUsersComponent
import com.kashapovrush.search_users_features.di.SearchUsersComponentProvider


class SearchUsersApp: Application(), SearchUsersComponentProvider {
    override fun getSearchUsersComponent(): SearchUsersComponent {
        return DaggerSearchUsersComponent.factory().create(this)
    }

import com.kashapovrush.user_repositories_features.di.DaggerUserRepositoriesComponent
import com.kashapovrush.user_repositories_features.di.UserRepositoriesComponent
import com.kashapovrush.user_repositories_features.di.UserRepositoriesComponentProvider

class SearchUsersApp: Application(), SearchUsersComponentProvider, UserRepositoriesComponentProvider {
    override fun getSearchUsersComponent(): SearchUsersComponent {
        return DaggerSearchUsersComponent.factory().create(this)
    }

    override fun getUserRepositoriesComponent(): UserRepositoriesComponent {
        return DaggerUserRepositoriesComponent.factory().create(this)
    }

}