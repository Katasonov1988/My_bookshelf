package com.example.mybookshelf.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mybookshelf.data.repository.BookPagingSource
import com.example.mybookshelf.data.network.GoogleapisService
import com.example.mybookshelf.data.model.BookList
import kotlinx.coroutines.flow.Flow

class GoogleapisRepositoryImpl(private val googleapisService: GoogleapisService) {
    fun getSearchResultStream(query: String): Flow<PagingData<BookList>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BookPagingSource(googleapisService, query)
            }
        ).flow

    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}