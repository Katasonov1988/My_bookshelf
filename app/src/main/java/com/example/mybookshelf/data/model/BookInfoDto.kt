package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookInfoDto (
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("authors") val authors: List<String>?,
    @field:SerializedName("publishedDate") val publishedDate: String?,
    @field:SerializedName("description") val description: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLinksDto?
)
