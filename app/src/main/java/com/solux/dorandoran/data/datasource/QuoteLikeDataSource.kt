package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.ResponsePostDelQuoteLikeDto

interface QuoteLikeDataSource {
    suspend fun addQuoteLike(quoteId: Long, accessToken: String): ResponsePostDelQuoteLikeDto
    suspend fun removeQuoteLike(quoteId: Long, accessToken: String): ResponsePostDelQuoteLikeDto
}