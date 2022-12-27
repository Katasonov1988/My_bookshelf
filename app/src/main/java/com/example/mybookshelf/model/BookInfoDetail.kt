package com.example.mybookshelf.model
import com.google.gson.annotations.SerializedName


data class BookInfoDetail(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("subtitle") val subtitle: String,
    @field:SerializedName("authors") val authors: List<String>,
    @field:SerializedName("publishedDate") val publishedDate: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("pageCount") val pageCount: Int,
    @field:SerializedName("maturityRating") val maturityRatin: String,
    @SerializedName("imageLinks") val imageLinksDetail: ImageLinksDetail,
    @field:SerializedName("language") val language: String,
    @field:SerializedName("previewLink") val previewLink: String,
    @field:SerializedName("infoLink") val infoLink: String
)

