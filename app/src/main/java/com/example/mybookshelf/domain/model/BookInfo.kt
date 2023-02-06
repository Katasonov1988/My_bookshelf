package com.example.mybookshelf.domain.model

data class BookInfo(
    val title: String?,
    val authors: List<String>?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: ImageLinks
)
