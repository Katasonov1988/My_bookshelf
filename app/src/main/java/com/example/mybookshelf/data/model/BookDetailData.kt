package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookDetailData(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val bookInfoDetail: BookInfoDetailData,
)
