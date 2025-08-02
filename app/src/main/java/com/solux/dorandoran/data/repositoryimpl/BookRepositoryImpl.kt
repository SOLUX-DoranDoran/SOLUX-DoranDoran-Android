package com.solux.dorandoran.data.repositoryimpl

import android.content.Context
import com.solux.dorandoran.data.datasource.BookDataSource
import com.solux.dorandoran.data.mapper.toBookInfoEntity
import com.solux.dorandoran.domain.entity.BookInfoEntity
import com.solux.dorandoran.domain.repository.BookRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDataSource: BookDataSource,
    @ApplicationContext private val context: Context
) : BookRepository {

    private suspend fun getAccessToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is"
    }

    override suspend fun getBookInfo(bookId: Int): Result<BookInfoEntity> {
        return runCatching {
            val token = getAccessToken()
            val response = bookDataSource.getBookInfo(token, bookId)
            response.toBookInfoEntity()
        }
    }

}