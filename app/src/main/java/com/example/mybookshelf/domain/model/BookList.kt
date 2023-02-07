package com.example.mybookshelf.domain.model

data class BookList(
    val id: String,
    val title: String?,
    val authors: String?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: String? = null
)
