package com.example.mybookshelf.domain

import androidx.paging.PagingData
import com.example.mybookshelf.domain.model.BookList
import kotlinx.coroutines.flow.Flow

interface GoogleapisRepository {

    fun getSearchResultStream(query: String): Flow<PagingData<BookList>>

    fun getDetailBookInfo(bookId: String): DetailBookItem
}