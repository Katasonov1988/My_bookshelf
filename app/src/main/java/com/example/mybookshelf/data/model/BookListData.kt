package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookListData(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: BookInfoData
)
