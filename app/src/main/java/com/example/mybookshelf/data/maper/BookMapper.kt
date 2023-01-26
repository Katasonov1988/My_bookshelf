package com.example.mybookshelf.data.maper

import com.example.mybookshelf.data.model.BookDetailDto
import com.example.mybookshelf.data.model.BookInfoDto
import com.example.mybookshelf.data.model.BookListDto
import com.example.mybookshelf.data.model.ImageLinksDto
import com.example.mybookshelf.domain.model.BookList
import com.example.mybookshelf.domain.DetailBookItem
import com.example.mybookshelf.domain.model.BookInfo
import com.example.mybookshelf.domain.model.ImageLinks

//class BookMapper {
//    fun mapBookListDtoToBookInfoItemEntity(bookListDto: BookListDto) = BookList(
//        bookId = bookListDto.id,
//        title = bookListDto.volumeInfo.title,
//        authors = mapAuthorsListToString(bookListDto.volumeInfo.authors),
//        publishedDate = bookListDto.volumeInfo.publishedDate,
//        description = bookListDto.volumeInfo.description,
//        imageLinks = bookListDto.volumeInfo.imageLinks?.thumbnail
//    )
//
//    fun mapAuthorsListToString(listAuthors: List<String>?): String {
//        return listAuthors?.joinToString(",") ?: ""
//    }
//
//    fun mapBookDetailDtoToDetailBookEntity(bookDetailDto: BookDetailDto) = DetailBookItem(
//        bookId = bookDetailDto.id,
//        imageLinks = bookDetailDto.bookInfoDetail.imageLinksDetail.small,
//        title = bookDetailDto.bookInfoDetail.title,
//        authors = mapAuthorsListToString(bookDetailDto.bookInfoDetail.authors),
//        publishedDate = bookDetailDto.bookInfoDetail.publishedDate,
//        description = bookDetailDto.bookInfoDetail.description,
//        pageCount = bookDetailDto.bookInfoDetail.pageCount,
//        language = bookDetailDto.bookInfoDetail.language,
//        infoLink = bookDetailDto.bookInfoDetail.infoLink
//    )
//}

internal fun BookListDto.toBookList(): BookList {
   return BookList(
       id = id,
       volumeInfo = this.volumeInfo.toBookInfo()
   )
}

private fun BookInfoDto.toBookInfo(): BookInfo {
    return BookInfo(
        title = title,
        authors = authors,
        publishedDate = publishedDate,
        description = description,
        imageLinks = this.imageLinks?.toImageLinks()
    )
}

private fun ImageLinksDto.toImageLinks(): ImageLinks {
    return ImageLinks(
        thumbnail = thumbnail,
        small = small
    )
}