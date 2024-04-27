package com.kashapovrush.search_users_features.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashapovrush.common.adapter.searchusers.SearchStateAdapter
import com.kashapovrush.common.adapter.searchusers.SearchUsersAdapter
import com.kashapovrush.common.viewmodel.SearchUsersViewModel
import com.kashapovrush.search_users_features.databinding.FragmentSearchUsersBinding
import com.kashapovrush.search_users_features.di.SearchUsersComponentProvider
import com.kashapovrush.utils.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchUsersFragment : Fragment() {

    private lateinit var binding: FragmentSearchUsersBinding
    private lateinit var searchUsersAdapter: SearchUsersAdapter
    private lateinit var viewModel: SearchUsersViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        (requireActivity().application as SearchUsersComponentProvider).getSearchUsersComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchUsersViewModel::class.java]

        binding.searchViewUsers.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    viewModel.getUser(query ?: "").collectLatest {
                        searchUsersAdapter.submitData(it)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }
    private fun setRecyclerView() {
        searchUsersAdapter = SearchUsersAdapter(requireContext())
        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchUsersAdapter.withLoadStateFooter(SearchStateAdapter())
        }

        searchUsersAdapter.addLoadStateListener { state ->
            binding.rvUsers.isVisible = state.refresh != LoadState.Loading
            binding.progressBar.isVisible = state.refresh == LoadState.Loading
        }

        searchUsersAdapter.onClickListenerToItem = { login ->
            (requireActivity() as ClickListenerFromSearchUsers).clickListenerToUserRepositories(login)
        }
    }

    interface ClickListenerFromSearchUsers {

        fun clickListenerToUserRepositories(login: String)
    }

    companion object {

        fun newInstance() = SearchUsersFragment()
    }
}