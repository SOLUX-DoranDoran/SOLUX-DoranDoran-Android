package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.response.AddDiscussionResponseGetDto
import com.solux.dorandoran.data.dto.response.BookDiscussionListResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussDetailResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussionListResponseGetDto
import com.solux.dorandoran.domain.entity.DiscussPageEntity
import retrofit2.Response

interface DiscussDataSource {
    suspend fun getDiscussions(
        token: String,
        page: Int,
        size: Int
    ):List<DiscussionListResponseGetDto>

    suspend fun getDiscussionsForBook(
        token: String,
        bookId: Int
    ): BookDiscussionListResponseGetDto

    suspend fun createDiscussion(
        token: String,
        title: String,
        content: String,
        bookTitle: String
    ): AddDiscussionResponseGetDto

    suspend fun getDiscussionDetails(
        token: String,
        boardId: Int
    ): List<DiscussDetailResponseGetDto>

}