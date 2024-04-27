package com.kashapovrush.auth_feature.ui

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kashapovrush.auth_feature.databinding.FragmentAuthBinding
import com.kashapovrush.auth_feature.di.AuthComponentProvider
import com.kashapovrush.common.utils.launchAndCollectIn
import com.kashapovrush.common.viewmodel.AuthViewModel
import com.kashapovrush.utils.ViewModelFactory
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.ResponseTypeValues
import javax.inject.Inject

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val getAuthResponse =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val dataIntent = it.data ?: return@registerForActivityResult
            handleAuthResponseIntent(dataIntent)
        }

    override fun onAttach(context: Context) {
        (requireActivity().application as AuthComponentProvider).getAuthComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]

        binding.btnAuth.setOnClickListener {
            viewModel.openLoginPage()
        }   

        binding.btnWithoutAuth.setOnClickListener {
            (requireActivity() as ClickListenerFromAuthFragment).clickListenerToSearchUsers()
        }

        viewModel.openAuthPageFlow.launchAndCollectIn(viewLifecycleOwner) {
            openAuthPage(it)
        }

        viewModel.authSuccessFlow.launchAndCollectIn(viewLifecycleOwner) {
            (requireActivity() as ClickListenerFromAuthFragment).clickListenerToAuthUser()
        }

        viewModel.toastFlow.launchAndCollectIn(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }


    fun handleAuthResponseIntent(intent: Intent) {
        val exception = AuthorizationException.fromIntent(intent)

        val tokenExchangeRequest =
            AuthorizationResponse.fromIntent(intent)?.createTokenExchangeRequest()

        when {
            // exception
            exception != null -> viewModel.onAuthCodedFailed(exception)

            // Success
            tokenExchangeRequest != null -> {
                viewModel.onAuthCodeReceived(tokenExchangeRequest)
            }
        }
    }

    private fun openAuthPage(intent: Intent) {
        getAuthResponse.launch(intent)
    }


    interface ClickListenerFromAuthFragment {
        fun clickListenerToAuthUser()

        fun clickListenerToSearchUsers()
    }

    companion object {
        fun newInstance() = AuthFragment()
    }
}