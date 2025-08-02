package com.solux.dorandoran.data.datasourceimpl

import com.solux.dorandoran.data.datasource.BookDataSource
import com.solux.dorandoran.data.dto.response.BookInfoResponseGetDto
import com.solux.dorandoran.data.service.BookApiService
import retrofit2.Response
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(
    private val bookApiService: BookApiService
) : BookDataSource {
    override suspend fun getBookInfo(
        token: String,
        bookId: Int
    ): BookInfoResponseGetDto {
        return bookApiService.getBookInfo(token, bookId)
    }
}