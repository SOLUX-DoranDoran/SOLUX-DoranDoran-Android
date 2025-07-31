package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.QuoteEntity

interface QuoteRepository {
    suspend fun postQuote(content: String, bookId: Long): Result<Long>
    suspend fun getQuotes(
        sort: String = "recent",
        page: Int = 1,
        size: Int = 10
    ): Result<List<QuoteEntity>>
}