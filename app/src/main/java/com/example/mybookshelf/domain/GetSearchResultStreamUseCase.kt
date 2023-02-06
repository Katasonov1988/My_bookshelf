package com.example.mybookshelf.domain

import androidx.paging.PagingData
import com.example.mybookshelf.domain.model.BookList
import kotlinx.coroutines.flow.Flow

class GetSearchResultStreamUseCase(
    private val googleapisRepository: GoogleapisRepository
) {
    fun getSearchResultStream(query: String): Flow<PagingData<BookList>> {
        return googleapisRepository.getSearchResultStream(query)
    }
}