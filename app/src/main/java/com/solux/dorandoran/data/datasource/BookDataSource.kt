package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.response.BookInfoResponseGetDto
import retrofit2.Response

interface BookDataSource {
    suspend fun getBookInfo(
        token: String,
        bookId: Int
    ): BookInfoResponseGetDto
}