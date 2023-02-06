package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookInfoListData(
    @SerializedName("totalItems") val totalItems: Int = 0,
    @SerializedName("items") val items: List<BookListData> = emptyList()
)
