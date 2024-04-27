package com.kashapovrush.common.adapter.searchusers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kashapovrush.common.databinding.ItemErrorBinding
import com.kashapovrush.common.databinding.ItemProgressBinding

class SearchStateAdapter: LoadStateAdapter<SearchStateAdapter.ItemViewHolder>() {

    override fun onBindViewHolder(holder: ItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ItemViewHolder {
        return when(loadState) {
            is LoadState.Error -> ErrorViewHolder(LayoutInflater.from(parent.context), parent)
            LoadState.Loading -> ProgressViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.NotLoading -> error("not supported")
        }
    }

    override fun getStateViewType(loadState: LoadState): Int = when(loadState) {
        is LoadState.Error -> ERROR
        LoadState.Loading -> PROGRESS
        is LoadState.NotLoading -> error("not supported")
    }

    abstract class ItemViewHolder(view:View): RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    class ProgressViewHolder constructor(
        private val binding: ItemProgressBinding
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {
            // Do nothing
        }

        companion object {

            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ProgressViewHolder {
                return ProgressViewHolder(
                    ItemProgressBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    class ErrorViewHolder constructor(
        private val binding: ItemErrorBinding
    ) : ItemViewHolder(binding.root) {

        override fun bind(loadState: LoadState) {
            require(loadState is LoadState.Error)
            binding.errorMessage.text = loadState.error.localizedMessage
        }

        companion object {

            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ErrorViewHolder {
                return ErrorViewHolder(
                    ItemErrorBinding.inflate(
                        layoutInflater,
                        parent,
                        attachToRoot
                    )
                )
            }
        }
    }

    companion object {
        const val PROGRESS = 1
        const val ERROR = 0
    }
}