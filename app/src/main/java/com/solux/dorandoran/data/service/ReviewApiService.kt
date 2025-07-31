package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.request.RequestCreateReviewDto
import com.solux.dorandoran.data.dto.response.ResponseCreateReviewDto
import com.solux.dorandoran.data.dto.response.ResponseGetBookReviewsDto
import com.solux.dorandoran.data.dto.response.ResponseGetRecentReviewDto
import com.solux.dorandoran.data.dto.response.ResponseGetReviewCommentsDto
import com.solux.dorandoran.data.dto.response.ResponseGetReviewDetailDto
import com.solux.dorandoran.data.dto.response.ResponseReviewLikeDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApiService {

    // 최근 리뷰 목록 조회
    @GET("/api/reviews")
    suspend fun getRecentReviews(
        @Header("Authorization") authorization: String,
        @Query("sort") sort: String = "recent",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): List<ResponseGetRecentReviewDto>

    // 특정 도서의 리뷰 목록 조회
    @GET("/api/books/{bookId}/reviews")
    suspend fun getBookReviews(
        @Header("Authorization") authorization: String,
        @Path("bookId") bookId: Long,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): ResponseGetBookReviewsDto

    // 특정 리뷰 상세 조회
    @GET("/api/reviews/{reviewId}")
    suspend fun getReviewDetail(
        @Header("Authorization") authorization: String,
        @Path("reviewId") reviewId: Long
    ): ResponseGetReviewDetailDto

    // 리뷰 좋아요 등록
    @POST("/api/reviews/{reviewId}/like")
    suspend fun addReviewLike(
        @Header("Authorization") authorization: String,
        @Path("reviewId") reviewId: Long
    ): ResponseReviewLikeDto

    // 리뷰 좋아요 취소
    @DELETE("/api/reviews/{reviewId}/like")
    suspend fun removeReviewLike(
        @Header("Authorization") authorization: String,
        @Path("reviewId") reviewId: Long
    ): ResponseReviewLikeDto

    // 리뷰 댓글 목록 조회
    @GET("/api/reviews/{reviewId}/comments")
    suspend fun getReviewComments(
        @Header("Authorization") authorization: String,
        @Path("reviewId") reviewId: Long,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): ResponseGetReviewCommentsDto

    // 리뷰 작성
    @POST("/api/books/{bookId}/reviews")
    suspend fun createReview(
        @Header("Authorization") authorization: String,
        @Path("bookId") bookId: Long,
        @Body request: RequestCreateReviewDto
    ): ResponseCreateReviewDto
}