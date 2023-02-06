package com.example.mybookshelf.data.maper

import com.example.mybookshelf.data.model.BookDetailData
import com.example.mybookshelf.data.model.BookInfoData
import com.example.mybookshelf.data.model.BookListData
import com.example.mybookshelf.data.model.ImageLinksData
import com.example.mybookshelf.domain.model.BookDetailItem
import com.example.mybookshelf.domain.model.BookList
import com.example.mybookshelf.domain.model.BookInfo
import com.example.mybookshelf.domain.model.ImageLinks

private const val EMPTY_TEXT = ""
private const val SPACE = " "

internal fun BookListData.toBookList(): BookList {
    return BookList(
        id = id,
        volumeInfo = this.volumeInfo.toBookInfo()
    )
}

internal fun BookDetailData.toBookDetailItem(): BookDetailItem {
    return BookDetailItem(
        bookId = id,
        imageLinks = bookInfoDetail.imageLinks?.toImageLinks(),
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
        authors = authors,
        publishedDate = publishedDate,
        description = description,
        imageLinks = this.imageLinks?.toImageLinks()
    )
}

private fun ImageLinksData.toImageLinks(): ImageLinks {
    return ImageLinks(
        thumbnail = thumbnail,
        small = small
    )
}

private fun listAuthorsToString(list: List<String>?): String {
    var text = EMPTY_TEXT
    if (list != null) {
        for (i in list.indices) {
            text = text.plus(list[i] + SPACE)
        }
    }

    return text
}