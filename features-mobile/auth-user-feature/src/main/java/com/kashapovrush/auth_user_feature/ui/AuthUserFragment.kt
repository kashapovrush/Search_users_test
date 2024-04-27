package com.kashapovrush.auth_user_feature.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kashapovrush.auth_user_feature.databinding.FragmentAuthUserBinding
import com.kashapovrush.auth_user_feature.di.AuthUserComponentProvider
import com.kashapovrush.common.utils.launchAndCollectIn
import com.kashapovrush.common.viewmodel.AuthUserViewModel
import com.kashapovrush.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthUserFragment : Fragment() {

    private lateinit var binding: FragmentAuthUserBinding
    private lateinit var viewModel: AuthUserViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        (requireActivity().application as AuthUserComponentProvider).getAuthUserComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this, viewModelFactory)[AuthUserViewModel::class.java]

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getUserInfo()
        }

        viewModel.user.observe(viewLifecycleOwner) {
            Log.d("MainActivityTest", "login ${it.login}")
            binding.nameUser.text = it.id.toString()
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Log.d("MainActivityTest", "error $it")
        }

        viewModel.authSuccessFlow.launchAndCollectIn(viewLifecycleOwner) {
            Log.d("MainActivityTest", it.name)
        }
    }

    companion object {
        fun newInstance() = AuthUserFragment()
    }
}