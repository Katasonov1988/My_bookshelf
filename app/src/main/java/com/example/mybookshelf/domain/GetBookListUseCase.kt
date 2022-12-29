package com.example.mybookshelf.domain

class GetBookListUseCase(private val googleapisRepository: GoogleapisRepository) {
    fun getBookList(): List<BookInfoItem> {
     return googleapisRepository.getBookList()
    }
}