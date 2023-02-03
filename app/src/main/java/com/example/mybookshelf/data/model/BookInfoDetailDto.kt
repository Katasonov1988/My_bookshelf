package com.example.mybookshelf.data.model

import com.google.gson.annotations.SerializedName


data class BookInfoDetailDto(
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("subtitle") val subtitle: String?,
    @field:SerializedName("authors") val authors: List<String>?,
    @field:SerializedName("publishedDate") val publishedDate: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("pageCount") val pageCount: Int? = null,
    @field:SerializedName("maturityRating") val maturityRatin: String?,
    @SerializedName("imageLinks") val imageLinks: ImageLinksDto?,
    @field:SerializedName("language") val language: String?,
)

