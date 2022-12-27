package com.example.mybookshelf.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mybookshelf.api.GoogleapisService
import com.example.mybookshelf.model.BookList
import kotlinx.coroutines.flow.Flow

class GoogleapisRepository (private val googleapisService: GoogleapisService) {
    fun getSearchResultStream (query: String): Flow<PagingData<BookList>> {
        return Pager (
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
                ),
            pagingSourceFactory = {
                BookPagingSource(googleapisService,query)
            }
        ).flow

    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}