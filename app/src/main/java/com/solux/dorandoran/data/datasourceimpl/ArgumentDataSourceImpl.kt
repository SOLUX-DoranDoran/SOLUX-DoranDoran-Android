package com.solux.dorandoran.data.datasourceimpl

import com.solux.dorandoran.data.datasource.ArgumentDataSource
import com.solux.dorandoran.data.dto.request.AddArgumentRequestDto
import com.solux.dorandoran.data.dto.request.AddCommentRequestDto
import com.solux.dorandoran.data.dto.response.AddArgumentResponseGetDto
import com.solux.dorandoran.data.dto.response.AddCommentResponseGetDto
import com.solux.dorandoran.data.dto.response.ArgumentListResponseGetDto
import com.solux.dorandoran.data.service.ArgumentApiService
import retrofit2.Response
import javax.inject.Inject

class ArgumentDataSourceImpl @Inject constructor(
    private val argumentApiService: ArgumentApiService
) : ArgumentDataSource {
    override suspend fun getArgumentsForDiscussion(
        token: String,
        boardId: Int
    ): List<ArgumentListResponseGetDto> {
        return argumentApiService.getArgumentsForDiscussion(token, boardId)
    }

    override suspend fun createArgument(
        token: String,
        boardId: Int,
        content: String
    ): AddArgumentResponseGetDto {
        return argumentApiService.createArgument(token, boardId, request = AddArgumentRequestDto(content))
    }

    override suspend fun createComment(
        token: String,
        boardId: Int,
        content: String,
        parentId: Int
    ): AddCommentResponseGetDto {
        return argumentApiService.createComment(token, boardId, request = AddCommentRequestDto(content, parentId))
    }
}