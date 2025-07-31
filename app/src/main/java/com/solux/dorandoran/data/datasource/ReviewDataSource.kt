package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.request.RequestCreateReviewDto
import com.solux.dorandoran.data.dto.response.ResponseCreateReviewDto
import com.solux.dorandoran.data.dto.response.ResponseGetBookReviewsDto
import com.solux.dorandoran.data.dto.response.ResponseGetRecentReviewDto
import com.solux.dorandoran.data.dto.response.ResponseGetReviewCommentsDto
import com.solux.dorandoran.data.dto.response.ResponseGetReviewDetailDto
import com.solux.dorandoran.data.dto.response.ResponseReviewLikeDto

interface ReviewDataSource {

    // 최근 리뷰 목록 조회
    suspend fun getRecentReviews(
        token: String,
        sort: String = "recent",
        page: Int = 1,
        size: Int = 10
    ): List<ResponseGetRecentReviewDto>

    // 특정 도서의 리뷰 목록 조회
    suspend fun getBookReviews(
        token: String,
        bookId: Long,
        page: Int = 1,
        size: Int = 10
    ): ResponseGetBookReviewsDto

    // 특정 리뷰 상세 조회
    suspend fun getReviewDetail(
        token: String,
        reviewId: Long
    ): ResponseGetReviewDetailDto

    // 리뷰 좋아요 등록
    suspend fun addReviewLike(
        token: String,
        reviewId: Long
    ): ResponseReviewLikeDto

    // 리뷰 좋아요 취소
    suspend fun removeReviewLike(
        token: String,
        reviewId: Long
    ): ResponseReviewLikeDto

    // 리뷰 댓글 목록 조회
    suspend fun getReviewComments(
        token: String,
        reviewId: Long,
        page: Int = 1,
        size: Int = 10
    ): ResponseGetReviewCommentsDto

    // 리뷰 작성
    suspend fun createReview(
        token: String,
        bookId: Long,
        request: RequestCreateReviewDto
    ): ResponseCreateReviewDto
}