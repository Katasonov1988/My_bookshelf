package com.example.mybookshelf.domain

import com.example.mybookshelf.data.model.ImageLinks

data class DetailBookItem(
    val bookId: String,
    val imageLinks: ImageLinks,
    val title: String,
    val authors: String,
    val publishedDate: String?,
    val description: String,
    val pageCount: Int,
    val language: String,
    val infoLink: String

)
