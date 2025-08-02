package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.DiscussCommentEntity

interface ArgumentRepository {
    suspend fun getArgumentsForDiscussion(
        boardId: Int
    ): Result<List<DiscussCommentEntity>>

    suspend fun createArgument(
        boardId: Int,
        content: String
    ): Result<DiscussCommentEntity>

    suspend fun createComment(
        boardId: Int,
        content: String,
        parentId: Int
    ): Result<DiscussCommentEntity>
}
