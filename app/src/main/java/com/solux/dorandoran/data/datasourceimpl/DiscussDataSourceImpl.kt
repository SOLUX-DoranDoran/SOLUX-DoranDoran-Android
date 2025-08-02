package com.solux.dorandoran.data.datasourceimpl

import com.solux.dorandoran.data.datasource.DiscussDataSource
import com.solux.dorandoran.data.dto.request.AddDiscussionRequestDto
import com.solux.dorandoran.data.dto.response.AddDiscussionResponseGetDto
import com.solux.dorandoran.data.dto.response.BookDiscussionListResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussDetailResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussionListResponseGetDto
import com.solux.dorandoran.data.service.DiscussApiService
import retrofit2.Response
import javax.inject.Inject

class DiscussDataSourceImpl @Inject constructor(
    private val discussApiService: DiscussApiService
) : DiscussDataSource {
    override suspend fun getDiscussions(
        token: String,
        page: Int,
        size: Int
    ): List<DiscussionListResponseGetDto> {
        return discussApiService.getDiscussions(token, page, size)
    }

    override suspend fun getDiscussionsForBook(
        token: String,
        bookId: Int
    ): BookDiscussionListResponseGetDto {
        return discussApiService.getDiscussionsForBook(token, bookId)
    }

    override suspend fun createDiscussion(
        token: String,
        title: String,
        content: String,
        bookTitle: String
    ): AddDiscussionResponseGetDto {
        return discussApiService.createDiscussion(token, request = AddDiscussionRequestDto(title, content, bookTitle))
    }

    override suspend fun getDiscussionDetails(
        token: String,
        boardId: Int
    ): List<DiscussDetailResponseGetDto> {
        return discussApiService.getDiscussionDetails(token, boardId)
    }


}