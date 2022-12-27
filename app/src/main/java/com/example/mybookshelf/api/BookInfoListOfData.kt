package com.example.mybookshelf.api

import com.example.mybookshelf.model.BookList
import com.google.gson.annotations.SerializedName

data class BookInfoListOfData(
    @SerializedName("totalItems") val totalItems: Int = 0,
    @SerializedName("items") val items: List<BookList> = emptyList()
)
