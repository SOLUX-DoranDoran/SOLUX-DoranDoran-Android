package com.solux.dorandoran.data.repositoryimpl

import com.solux.dorandoran.data.datasource.QuoteDataSource
import com.solux.dorandoran.data.dto.request.RequestPostQuoteDto
import com.solux.dorandoran.data.mapper.toQuoteEntity
import com.solux.dorandoran.domain.entity.QuoteEntity
import com.solux.dorandoran.domain.repository.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteDataSource: QuoteDataSource
) : QuoteRepository {
    override suspend fun postQuote(content: String, bookId: Long): Result<Long> {
        return runCatching {
            val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is"
            val request = RequestPostQuoteDto(content = content, bookId = bookId)
            val response = quoteDataSource.postQuote(token, request)
            response.quoteId
        }
    }

    override suspend fun getQuotes(
        sort: String,
        page: Int,
        size: Int
    ): Result<List<QuoteEntity>> {
        return runCatching {
            val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is"
            val response = quoteDataSource.getQuotes(token, sort, page, size)
            response.map { it.toQuoteEntity() }
        }
    }
}