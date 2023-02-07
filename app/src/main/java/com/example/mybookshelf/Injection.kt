package com.example.mybookshelf

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.mybookshelf.data.network.GoogleapisService
import com.example.mybookshelf.data.repository.GoogleapisRepositoryImpl
import com.example.mybookshelf.ui.book_detail.BookDetailViewModelFactory
import com.example.mybookshelf.ui.search_books.ViewModelFactory

object Injection {
    private fun provideBookRepository(): GoogleapisRepositoryImpl {
        return GoogleapisRepositoryImpl(GoogleapisService.create())
    }

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideBookRepository())
    }

    fun provideBookDetailViewModelFactory(): ViewModelProvider.Factory {
        return BookDetailViewModelFactory(provideBookRepository())
    }
}