package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.QuoteLikeEntity

interface QuoteLikeRepository {
    suspend fun toggleQuoteLike(quoteId: Long): Result<QuoteLikeEntity>
}