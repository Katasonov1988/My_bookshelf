package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookDetail(
    @field:SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val bookInfoDetail: BookInfoDetail,
)
