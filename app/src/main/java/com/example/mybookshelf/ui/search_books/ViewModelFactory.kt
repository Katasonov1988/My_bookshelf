package com.example.mybookshelf.ui.search_books

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.mybookshelf.data.repository.GoogleapisRepositoryImpl
import com.example.mybookshelf.ui.SearchBooksViewModel

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: GoogleapisRepositoryImpl
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(SearchBooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchBooksViewModel(repository, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}