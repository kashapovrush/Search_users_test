package com.kashapovrush.user_repositories_features.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashapovrush.common.adapter.userrepository.UserRepositoriesAdapter
import com.kashapovrush.common.viewmodel.UserRepositoriesViewModel
import com.kashapovrush.user_repositories_features.databinding.FragmentUserRepositoriesBinding
import com.kashapovrush.user_repositories_features.di.UserRepositoriesComponentProvider
import com.kashapovrush.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentUserRepositoriesBinding
    private lateinit var viewModel: UserRepositoriesViewModel
    private lateinit var userRepositoriesAdapter: UserRepositoriesAdapter
    private val scope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        (requireActivity().application as UserRepositoriesComponentProvider).getUserRepositoriesComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRepositoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[UserRepositoriesViewModel::class.java]
        val login = arguments?.getString(EXTRA_LOGIN)

        setRecyclerView()
        scope.launch {
            viewModel.getUserRepositories(login ?: "")
        }

        viewModel.repositories.observe(viewLifecycleOwner) { response ->
            userRepositoriesAdapter.submitList(response)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun setRecyclerView() {
        userRepositoriesAdapter = UserRepositoriesAdapter()
        with(binding.rvUserRepositories) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userRepositoriesAdapter
        }
    }

    override fun onDetach() {
        super.onDetach()
        scope.cancel()
    }

    companion object {

        const val EXTRA_LOGIN = "login"

        fun newInstance(login: String) = UserRepositoriesFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_LOGIN, login)
            }
        }
    }
}