package com.kashapovrush.searchuserstest

import android.app.Application
import com.kashapovrush.search_users_features.di.DaggerSearchUsersComponent
import com.kashapovrush.search_users_features.di.SearchUsersComponent
import com.kashapovrush.search_users_features.di.SearchUsersComponentProvider

class SearchUsersApp: Application(), SearchUsersComponentProvider {
    override fun getSearchUsersComponent(): SearchUsersComponent {
        return DaggerSearchUsersComponent.factory().create(this)
    }
}