package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.request.RequestPostQuoteDto
import com.solux.dorandoran.data.dto.response.ResponseGetQuoteDto
import com.solux.dorandoran.data.dto.response.ResponsePostQuoteDto
import retrofit2.http.*

interface QuoteApiService {
    @POST("/api/quotes")
    suspend fun postQuote(
        @Header("Authorization") token: String,
        @Body request: RequestPostQuoteDto
    ): ResponsePostQuoteDto

    @GET("/api/quotes")
    suspend fun getQuotes(
        @Header("Authorization") token: String,
        @Query("sort") sort: String = "recent",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): List<ResponseGetQuoteDto>
}