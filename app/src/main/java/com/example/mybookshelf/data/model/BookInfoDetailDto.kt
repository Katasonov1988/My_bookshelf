package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName

data class BookInfoDetailDto(
    @SerializedName("title") val title: String? = null,
    @SerializedName("subtitle") val subtitle: String? = null,
    @SerializedName("authors") val authors: List<String>? = null,
    @SerializedName("publishedDate") val publishedDate: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("pageCount") val pageCount: Int? = null,
    @SerializedName("maturityRating") val maturityRatin: String? = null,
    @SerializedName("imageLinks") val imageLinks: ImageLinksDetailData,
    @SerializedName("language") val language: String? = null
)

