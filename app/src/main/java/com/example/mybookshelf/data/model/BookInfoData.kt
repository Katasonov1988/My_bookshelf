package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookInfoData(
    @SerializedName("title") val title: String? = null,
    @SerializedName("authors") val authors: List<String>? = null,
    @SerializedName("publishedDate") val publishedDate: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("imageLinks") val imageLinks: ImageLinksData
)
