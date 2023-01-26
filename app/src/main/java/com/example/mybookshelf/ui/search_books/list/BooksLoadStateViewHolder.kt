package com.example.mybookshelf.ui.search_books.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookshelf.R
import com.example.mybookshelf.databinding.BooksLoadStateFooterViewItemBinding

class BooksLoadStateViewHolder (
    private val binding: BooksLoadStateFooterViewItemBinding,
    retry: () -> Unit
        ): RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        with(binding) {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): BooksLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.books_load_state_footer_view_item, parent, false)
            val binding = BooksLoadStateFooterViewItemBinding.bind(view)
            return BooksLoadStateViewHolder(binding, retry)
        }
    }
}
