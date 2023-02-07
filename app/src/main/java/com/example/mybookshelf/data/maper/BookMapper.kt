package com.example.mybookshelf.data.maper

import com.example.mybookshelf.data.model.*
import com.example.mybookshelf.domain.model.*

private const val COMMA = ", "

internal fun BookListData.toBookList(): BookList {
    return BookList(
        id = id,
        title = volumeInfo.title,
        authors = listAuthorsToString(volumeInfo.authors),
        publishedDate = volumeInfo.publishedDate,
        description = volumeInfo.description,
        imageLinks = this.volumeInfo.imageLinks?.toIconString()
    )
}

internal fun BookDetailData.toBookDetailItem(): BookDetailItem {
    return BookDetailItem(
        bookId = id,
        imageLinks = this.bookInfoDetail.imageLinks?.toImageString(),
        title = bookInfoDetail.title,
        authors = listAuthorsToString(bookInfoDetail.authors),
        publishedDate = bookInfoDetail.publishedDate,
        description = bookInfoDetail.description,
        pageCount = bookInfoDetail.pageCount,
        language = bookInfoDetail.language
    )
}

private fun ImageLinksData.toIconString(): String {
    return thumbnail.toString()
}

private fun ImageLinksDetailData.toImageString(): String {
    return small.toString()

}

private fun listAuthorsToString(authors: List<String>?): String {
    return authors?.joinToString(COMMA).orEmpty()
}