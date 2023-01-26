package com.example.mybookshelf.domain

interface GoogleapisRepository {

    fun getBookList(): List<BookInfoItem>

    fun getDetailBookInfo(bookId: String): DetailBookItem
}