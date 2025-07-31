package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.BookReviewsResponseEntity
import com.solux.dorandoran.domain.entity.ReviewCommentsResponseEntity
import com.solux.dorandoran.domain.entity.ReviewCreateResponseEntity
import com.solux.dorandoran.domain.entity.ReviewDetailEntity
import com.solux.dorandoran.domain.entity.ReviewLikeResponseEntity
import com.solux.dorandoran.domain.entity.ReviewListEntity

interface ReviewRepository {

    // 최근 리뷰 목록 조회
    suspend fun getRecentReviews(
        sort: String = "recent",
        page: Int = 1,
        size: Int = 10
    ): Result<List<ReviewListEntity>>

    // 특정 도서의 리뷰 목록 조회
    suspend fun getBookReviews(
        bookId: Long,
        page: Int = 1,
        size: Int = 10
    ): Result<BookReviewsResponseEntity>

    // 특정 리뷰 상세 조회
    suspend fun getReviewDetail(
        reviewId: Long
    ): Result<ReviewDetailEntity>

    // 리뷰 좋아요 토글 (등록/취소 자동 판단)
    suspend fun toggleReviewLike(
        reviewId: Long
    ): Result<ReviewLikeResponseEntity>

    // 리뷰 댓글 목록 조회
    suspend fun getReviewComments(
        reviewId: Long,
        page: Int = 1,
        size: Int = 10
    ): Result<ReviewCommentsResponseEntity>

    // 리뷰 작성
    suspend fun createReview(
        bookId: Long,
        content: String,
        rating: Int
    ): Result<ReviewCreateResponseEntity>
}