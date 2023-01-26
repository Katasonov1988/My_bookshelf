package com.example.mybookshelf.domain

import com.example.mybookshelf.data.model.ImageLinks
import com.google.android.material.tabs.TabLayout

data class BookInfoItem(
    val book_id: String,
    val title: String?,
    val authors: String?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: ImageLinks?
)
