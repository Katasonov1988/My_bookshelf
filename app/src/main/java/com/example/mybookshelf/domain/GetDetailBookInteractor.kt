package com.example.mybookshelf.domain

import com.example.mybookshelf.domain.model.BookDetailItem

class GetDetailBookInteractor(
    private val googleapisRepository: GoogleapisRepository
) {
    suspend fun getDetailBookInfo(bookId: String): BookDetailItem {
        return googleapisRepository.getDetailBookInfo(bookId)
    }
}