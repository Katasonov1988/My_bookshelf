package com.example.mybookshelf.data.maper

import com.example.mybookshelf.data.model.*
import com.example.mybookshelf.domain.model.*

private const val COMMA = ", "

internal fun BookListData.toBookList(): BookList {
    return BookList(
        id = id,
        volumeInfo = this.volumeInfo.toBookInfo()
    )
}

internal fun BookDetailData.toBookDetailItem(): BookDetailItem {
    return BookDetailItem(
        bookId = id,
        imageLinks = this.bookInfoDetail.imageLinks.toImageLinksDetail(),
        title = bookInfoDetail.title,
        authors = listAuthorsToString(bookInfoDetail.authors),
        publishedDate = bookInfoDetail.publishedDate,
        description = bookInfoDetail.description,
        pageCount = bookInfoDetail.pageCount,
        language = bookInfoDetail.language
    )
}

private fun BookInfoData.toBookInfo(): BookInfo {
    return BookInfo(
        title = title,
        authors = listAuthorsToString(authors),
        publishedDate = publishedDate,
        description = description,
        imageLinks = imageLinks.toImageLinks()
    )
}

private fun ImageLinksData.toImageLinks(): ImageLinks {
    return ImageLinks(
        thumbnail = thumbnail
        )
}

private fun ImageLinksDetailData.toImageLinksDetail(): ImageLinksDetail {
    return ImageLinksDetail(
        smallThumbnail = smallThumbnail,
        thumbnail = thumbnail,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge

    )
}

private fun listAuthorsToString(authors: List<String>?): String {
    return authors?.joinToString(COMMA).orEmpty()
}