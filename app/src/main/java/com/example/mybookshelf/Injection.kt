package com.example.mybookshelf

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.mybookshelf.api.GoogleapisService
import com.example.mybookshelf.data.GoogleapisRepository
import com.example.mybookshelf.ui.ViewModelFactory

object Injection {
    private fun provideBookRepository(): GoogleapisRepository {
        return GoogleapisRepository(GoogleapisService.create())
    }

    fun provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideBookRepository())
    }
}