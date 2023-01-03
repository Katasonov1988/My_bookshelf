package com.example.mybookshelf.domain.model

import com.example.mybookshelf.data.model.ImageLinksDto

data class BookDetailItem(
    val bookId: String,
    val imageLinks: ImageLinks?,
    val title: String?,
    val authors: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val language: String?
)
