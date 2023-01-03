package com.example.mybookshelf.ui.book_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybookshelf.data.repository.GoogleapisRepositoryImpl
import com.example.mybookshelf.domain.model.BookDetailItem
import com.example.mybookshelf.domain.GetDetailBookUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BookDetailViewModel (
    private val repository: GoogleapisRepositoryImpl
        ): ViewModel() {

private val getDetailBookUseCase = GetDetailBookUseCase(repository)


    private val _bookItem = MutableLiveData<BookDetailItem>()
    val bookItem: LiveData<BookDetailItem>
        get() = _bookItem

fun getBookDetaiItem(bookId: String) {

    viewModelScope.launch {
        try {
          val item = getDetailBookUseCase.getDetailBookInfo(bookId)
            Log.d("BookItemInfo", item.toString())
            _bookItem.value = item
        } catch (e: IOException) {
            Log.d("BookItemInfo", "IOException response $e")
        } catch (e: HttpException) {
            Log.d("BookItemInfo", "HttpException response $e")

        }

    }

}

}