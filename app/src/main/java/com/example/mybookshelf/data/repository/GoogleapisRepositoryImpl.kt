package com.example.mybookshelf.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mybookshelf.data.network.GoogleapisService
import com.example.mybookshelf.data.model.BookListDto
import com.example.mybookshelf.domain.model.BookList
import com.example.mybookshelf.domain.DetailBookItem
import com.example.mybookshelf.domain.GoogleapisRepository
import kotlinx.coroutines.flow.Flow

class GoogleapisRepositoryImpl(private val googleapisService: GoogleapisService): GoogleapisRepository {
    override fun getSearchResultStream(query: String): Flow<PagingData<BookList>> {
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

    override fun getDetailBookInfo(bookId: String): DetailBookItem {
        TODO("Not yet implemented")
    }


    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}