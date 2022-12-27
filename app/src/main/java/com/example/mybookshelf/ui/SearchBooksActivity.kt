package com.example.mybookshelf.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookshelf.Injection
import com.example.mybookshelf.databinding.ActivitySearchBooksBinding
import com.example.mybookshelf.model.BookList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchBooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBooksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider(
            this, Injection.provideViewModelFactory(owner = this)
        ).get(SearchBooksViewModel::class.java)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.recyclerviewBooks.addItemDecoration(decoration)

        binding.bindState(
            uiState = viewModel.state,
            pagingData = viewModel.pagingDataFlow,
            uiActions = viewModel.accept
        )

    }

    private fun ActivitySearchBooksBinding.bindState(
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<BookList>>,
        uiActions: (UiAction) -> Unit
    ) {
        val booksAdapter = BooksAdapter()
        recyclerviewBooks.adapter = booksAdapter.withLoadStateHeaderAndFooter(
            header = BooksLoadStateAdapter {booksAdapter.retry()},
            footer = BooksLoadStateAdapter {booksAdapter.retry()}
        )

        bindSearch(
            uiState = uiState,
            onQueryChanged = uiActions
        )
        bindList(
            booksAdapter = booksAdapter,
            uiState = uiState,
            pagingData = pagingData,
            onScrollChanged = uiActions
        )
    }

    private fun ActivitySearchBooksBinding.bindSearch(
        uiState: StateFlow<UiState>,
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        edSearchBook.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateBookListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }
        edSearchBook.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateBookListFromInput(onQueryChanged)
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            uiState
                .map {
                    it.query


                }

                .distinctUntilChanged()
                .collect(edSearchBook::setText)
            Log.d("Querys",uiState.value.query )
        }
    }

    private fun ActivitySearchBooksBinding.updateBookListFromInput(
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        edSearchBook.text.trim().let {
            if (it.isNotEmpty()) {
                recyclerviewBooks.scrollToPosition(0)
                onQueryChanged(UiAction.Search(query = it.toString()))
            }
        }
    }

    private fun ActivitySearchBooksBinding.bindList(
        booksAdapter: BooksAdapter,
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<BookList>>,
        onScrollChanged: (UiAction.Scroll) -> Unit
    ) {
        recyclerviewBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy != 0) onScrollChanged(UiAction.Scroll(currentQuery = uiState.value.query))
            }
        })

        val notLoading = booksAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.source.refresh is LoadState.NotLoading }

        val hasNotScrolledForCurrentSearch = uiState
            .map { it.hasNotScrolledForCurrentSearch }
            .distinctUntilChanged()

        val shouldScrollToTop = combine(
            notLoading,
            hasNotScrolledForCurrentSearch,
            Boolean::and
        )
            .distinctUntilChanged()

        lifecycleScope.launch {
            pagingData.collectLatest(booksAdapter::submitData)
        }

        lifecycleScope.launch {
            shouldScrollToTop.collect { shouldScroll ->
                if (shouldScroll) recyclerviewBooks.scrollToPosition(0)
            }
        }
    }
}