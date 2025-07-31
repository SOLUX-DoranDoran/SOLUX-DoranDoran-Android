package com.solux.dorandoran.data.repositoryimpl

import com.solux.dorandoran.data.datasource.QuoteLikeDataSource
import com.solux.dorandoran.data.mapper.toQuoteLikeEntity
import com.solux.dorandoran.domain.entity.QuoteLikeEntity
import com.solux.dorandoran.domain.repository.QuoteLikeRepository
import javax.inject.Inject

class QuoteLikeRepositoryImpl @Inject constructor(
    private val quoteLikeDataSource: QuoteLikeDataSource
) : QuoteLikeRepository {

    override suspend fun toggleQuoteLike(quoteId: Long): Result<QuoteLikeEntity> {
        return runCatching {
            val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is"
            val isCurrentlyLiked = checkIfLiked(quoteId) // 현재 좋아요 상태 확인

            val response = if (isCurrentlyLiked) {
                quoteLikeDataSource.removeQuoteLike(quoteId, token)
            } else {
                quoteLikeDataSource.addQuoteLike(quoteId, token)
            }

            response.toQuoteLikeEntity()
        }
    }

    private suspend fun checkIfLiked(quoteId: Long): Boolean {
        return false
    }
}