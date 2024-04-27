package com.kashapovrush.searchuserstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.kashapovrush.auth_feature.ui.AuthFragment
import com.kashapovrush.auth_user_feature.ui.AuthUserFragment
import com.kashapovrush.search_users_features.ui.SearchUsersFragment
import com.kashapovrush.searchuserstest.databinding.ActivityMainBinding
import com.kashapovrush.user_repositories_features.ui.UserRepositoriesFragment



class MainActivity : AppCompatActivity(), SearchUsersFragment.ClickListenerFromSearchUsers, AuthFragment.ClickListenerFromAuthFragment {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.commit {
            replace(R.id.container, AuthFragment.newInstance())
        }

    }

    override fun clickListenerToUserRepositories(login: String) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container, UserRepositoriesFragment.newInstance(login))
        }
    }

    override fun clickListenerToAuthUser() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container, AuthUserFragment.newInstance())
        }
    }

    override fun clickListenerToSearchUsers() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container, SearchUsersFragment.newInstance())
        }
    }
}