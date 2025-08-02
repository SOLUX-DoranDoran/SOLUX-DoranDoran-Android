package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.response.BookInfoResponseGetDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface BookApiService {

    @GET("/api/books/{bookId}")
    suspend fun getBookInfo(
        @Header("Authorization") token: String,
        @Path("bookId") bookId: Int
    ): BookInfoResponseGetDto

}