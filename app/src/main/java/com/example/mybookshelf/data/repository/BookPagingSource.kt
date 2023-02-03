package com.example.mybookshelf.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mybookshelf.data.maper.toBookList
import com.example.mybookshelf.data.network.GoogleapisService
import com.example.mybookshelf.data.repository.GoogleapisRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import com.example.mybookshelf.domain.model.BookList
import retrofit2.HttpException
import java.io.IOException

private const val GOOGLEAPIAS_STARTING_PAGE_INDEX = 0


class BookPagingSource(
    private val googleapisService: GoogleapisService,
    private val query: String
) : PagingSource<Int, BookList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookList> {
        Log.d("ShowBook", "запущен метод load в BookPagingSource")
        val position = params.key ?: GOOGLEAPIAS_STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val response = googleapisService.getBookList(apiQuery, position, params.loadSize)
            val listBooks = response.items.map { it.toBookList() }
            Log.d("ShowBook", response.toString())
            val nextKey = if (listBooks.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = listBooks,
                prevKey = if (position == GOOGLEAPIAS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            Log.d("ShowBook", "response $exception")
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.d("ShowBook", "responseses $exception")
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, BookList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}