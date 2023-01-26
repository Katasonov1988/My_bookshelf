package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookListDto(
    @field:SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: BookInfoDto
)
