package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.BookInfoEntity

interface BookRepository {
    suspend fun getBookInfo(
        bookId: Int
    ): Result<BookInfoEntity>
}
