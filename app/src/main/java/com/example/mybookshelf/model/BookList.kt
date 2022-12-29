package com.example.mybookshelf.model

import com.example.mybookshelf.model.BookInfo
import com.google.gson.annotations.SerializedName

data class BookList(
    @field:SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: BookInfo

    )
