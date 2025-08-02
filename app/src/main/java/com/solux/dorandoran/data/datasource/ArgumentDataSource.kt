package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.response.AddArgumentResponseGetDto
import com.solux.dorandoran.data.dto.response.AddCommentResponseGetDto
import com.solux.dorandoran.data.dto.response.ArgumentListResponseGetDto
import retrofit2.Response

interface ArgumentDataSource {

    suspend fun getArgumentsForDiscussion(
        token: String,
        boardId: Int
    ): List<ArgumentListResponseGetDto>

    suspend fun createArgument(
        token: String,
        boardId: Int,
        content: String
    ): AddArgumentResponseGetDto

    suspend fun createComment(
        token: String,
        boardId: Int,
        content: String,
        parentId: Int
    ): AddCommentResponseGetDto

}