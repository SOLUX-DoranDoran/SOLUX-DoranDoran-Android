package com.solux.dorandoran.data.datasourceimpl

import com.solux.dorandoran.data.service.QuoteLikeApiService
import com.solux.dorandoran.data.datasource.QuoteLikeDataSource
import com.solux.dorandoran.data.dto.ResponsePostDelQuoteLikeDto
import javax.inject.Inject

class QuoteLikeDataSourceImpl @Inject constructor(
    private val quoteLikeApiService: QuoteLikeApiService
) : QuoteLikeDataSource {
    override suspend fun addQuoteLike(
        quoteId: Long,
        accessToken: String
    ): ResponsePostDelQuoteLikeDto {
        return quoteLikeApiService.addQuoteLike(
            authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is",
            quoteId = quoteId
        )
    }

    override suspend fun removeQuoteLike(
        quoteId: Long,
        accessToken: String
    ): ResponsePostDelQuoteLikeDto {
        return quoteLikeApiService.removeQuoteLike(
            authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is",
            quoteId = quoteId
        )
    }
}