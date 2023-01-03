package com.example.mybookshelf.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.mybookshelf.data.maper.toBookDetailItem
import com.example.mybookshelf.data.network.GoogleapisService
import com.example.mybookshelf.domain.model.BookList
import com.example.mybookshelf.domain.model.BookDetailItem
import com.example.mybookshelf.domain.GoogleapisRepository
import kotlinx.coroutines.flow.Flow

class GoogleapisRepositoryImpl(private val googleapisService: GoogleapisService) :
    GoogleapisRepository {
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

    override suspend fun getDetailBookInfo(bookId: String): BookDetailItem {

//        return try {
           return googleapisService.getDetailBookInfo(bookId).toBookDetailItem()



//        } catch (exception: IOException) {
//
//            return PagingSource.LoadResult.Error(exception)
//        } catch (exception: HttpException) {
//
//            return PagingSource.LoadResult.Error(exception)
//        }


    }


    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

}