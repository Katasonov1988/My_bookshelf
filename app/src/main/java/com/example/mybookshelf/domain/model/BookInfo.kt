package com.example.mybookshelf.domain.model

import com.example.mybookshelf.data.model.ImageLinksDto
import com.google.gson.annotations.SerializedName

data class BookInfo(
    val title: String?,
    val authors: List<String>?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: ImageLinks?
)
