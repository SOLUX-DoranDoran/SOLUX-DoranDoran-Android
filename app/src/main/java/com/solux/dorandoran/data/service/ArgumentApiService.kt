package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.request.AddCommentRequestDto
import com.solux.dorandoran.data.dto.request.AddArgumentRequestDto
import com.solux.dorandoran.data.dto.response.AddCommentResponseGetDto
import com.solux.dorandoran.data.dto.response.AddArgumentResponseGetDto
import com.solux.dorandoran.data.dto.response.ArgumentListResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussionListResponseGetDto
import com.solux.dorandoran.domain.repository.ArgumentRepository
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ArgumentApiService {

    @GET("/api/boards/{boardId}/comments")
    suspend fun getArgumentsForDiscussion(
        @Header("Authorization") token: String,
        @Path("boardId") boardId: Int
    ): List<ArgumentListResponseGetDto>

    @POST("/api/boards/{boardId}/comments")
    suspend fun createArgument(
        @Header("Authorization") token: String,
        @Path("boardId") boardId: Int,
        @Body request: AddArgumentRequestDto
    ): AddArgumentResponseGetDto

    @POST("/api/boards/{boardId}/comments")
    suspend fun createComment(
        @Header("Authorization") token: String,
        @Path("boardId") boardId: Int,
        @Body request: AddCommentRequestDto
    ): AddCommentResponseGetDto

}