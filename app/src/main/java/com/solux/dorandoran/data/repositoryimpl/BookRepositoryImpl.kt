package com.solux.dorandoran.data.repositoryimpl

import com.solux.dorandoran.data.datasource.BookDataSource
import com.solux.dorandoran.data.mapper.toBookEntity
import com.solux.dorandoran.domain.entity.BookEntity
import com.solux.dorandoran.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDataSource: BookDataSource
) : BookRepository {
    override suspend fun getBookInfo(bookId: Long): Result<BookEntity> {
        return runCatching {
            bookDataSource.getBookInfo(bookId).toBookEntity()
        }
    }
}