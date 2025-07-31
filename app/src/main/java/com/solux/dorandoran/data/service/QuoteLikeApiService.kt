package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.ResponsePostDelQuoteLikeDto
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface QuoteLikeApiService {
    @POST("api/quotes/{quoteId}/like")
    suspend fun addQuoteLike(
        @Header("Authorization") authorization: String,
        @Path("quoteId") quoteId: Long
    ): ResponsePostDelQuoteLikeDto

    @DELETE("api/quotes/{quoteId}/like")
    suspend fun removeQuoteLike(
        @Header("Authorization") authorization: String,
        @Path("quoteId") quoteId: Long
    ): ResponsePostDelQuoteLikeDto
}