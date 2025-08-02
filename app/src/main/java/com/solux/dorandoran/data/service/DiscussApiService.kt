package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.request.DiscussionListRequestDto
import com.solux.dorandoran.data.dto.request.AddDiscussionRequestDto
import com.solux.dorandoran.data.dto.response.DiscussionListResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussDetailResponseGetDto
import com.solux.dorandoran.data.dto.response.BookDiscussionListResponseGetDto
import com.solux.dorandoran.data.dto.response.AddDiscussionResponseGetDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DiscussApiService {

    @GET("/api/boards?page=1&size=10")
    suspend fun getDiscussions(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): List<DiscussionListResponseGetDto>

    @GET("/api/books/{bookId}/board")
    suspend fun getDiscussionsForBook(
        @Header("Authorization") token: String,
        @Path("bookId") bookId: Int
    ): BookDiscussionListResponseGetDto

    @POST("/api/boards")
    suspend fun createDiscussion(
        @Header("Authorization") token: String,
        @Body request: AddDiscussionRequestDto
    ): AddDiscussionResponseGetDto

    @GET("/api/boards/{boardId}")
    suspend fun getDiscussionDetails(
        @Header("Authorization") token: String,
        @Path("boardId") boardId: Int
    ) : List<DiscussDetailResponseGetDto>
}