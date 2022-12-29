package com.example.mybookshelf.domain

class GetDetailBookUseCase(private val googleapisRepository: GoogleapisRepository) {
    fun getDetailBookInfo(bookId: String): DetailBookItem {
        return googleapisRepository.getDetailBookInfo(bookId)
    }
}