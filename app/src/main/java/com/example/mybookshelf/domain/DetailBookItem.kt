package com.example.mybookshelf.domain

import com.example.mybookshelf.data.model.ImageLinksDto

data class DetailBookItem(
    val bookId: String,
    val imageLinks: String,
    val title: String,
    val authors: String,
    val publishedDate: String?,
    val description: String,
    val pageCount: Int,
    val language: String,
    val infoLink: String

)
