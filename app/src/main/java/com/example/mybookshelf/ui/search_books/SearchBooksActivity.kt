package com.example.mybookshelf.ui.search_books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.mybookshelf.Injection
import com.example.mybookshelf.databinding.ActivitySearchBooksBinding
import com.example.mybookshelf.data.model.BookList
import com.example.mybookshelf.ui.SearchBooksViewModel
import com.example.mybookshelf.ui.UiAction
import com.example.mybookshelf.ui.UiState
import com.example.mybookshelf.ui.book_detail.BookDetailActivity
import com.example.mybookshelf.ui.search_books.list.BooksAdapter
import com.example.mybookshelf.ui.search_books.list.BooksLoadStateAdapter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchBooksActivity : AppCompatActivity() {

    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBooksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModel = ViewModelProvider(
            this, Injection.provideViewModelFactory(owner = this)
        ).get(SearchBooksViewModel::class.java)

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvBooks.addItemDecoration(decoration)

        binding.bindState(
            uiState = viewModel.state,
            pagingData = viewModel.pagingDataFlow,
            uiActions = viewModel.accept
        )
        setupItemShortClickListener()

    }

    private fun ActivitySearchBooksBinding.bindState(
        uiState: StateFlow<UiState>,
        pagingData: Flow<PagingData<BookList>>,
        uiActions: (UiAction) -> Unit
    ) {
        booksAdapter = BooksAdapter()
        rvBooks.adapter = booksAdapter.withLoadStateHeaderAndFooter(
            header = BooksLoadStateAdapter { booksAdapter.retry() },
            footer = BooksLoadStateAdapter { booksAdapter.retry() }
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
            Log.d("Queries", uiState.value.query)
        }
    }

    private fun ActivitySearchBooksBinding.updateBookListFromInput(
        onQueryChanged: (UiAction.Search) -> Unit
    ) {
        edSearchBook.text.trim().let {
            if (it.isNotEmpty()) {
                rvBooks.scrollToPosition(0)
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
        rvBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
                if (shouldScroll) rvBooks.scrollToPosition(0)
            }
        }
    }

    private fun setupItemShortClickListener() {
        booksAdapter.onBookItemClickListener = {
            val intent = BookDetailActivity.newIntent(this, it.id)
            Log.d("BooksId", it.id)
            Toast.makeText(this, "id книги: ${it.id}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

    }
}