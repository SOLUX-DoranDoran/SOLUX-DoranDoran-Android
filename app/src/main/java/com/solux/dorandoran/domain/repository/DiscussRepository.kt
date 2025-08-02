package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.DiscussPageEntity

interface DiscussRepository {

    suspend fun getDiscussions(
        page: Int,
        size: Int
    ): Result<List<DiscussPageEntity>>


    suspend fun getDiscussionsForBook(
        bookId: Int
    ): Result<DiscussPageEntity>

    suspend fun createDiscussion(
        title: String,
        content: String,
        bookTitle: String
    ): Result<DiscussPageEntity>

    suspend fun getDiscussionDetails(
        boardId: Int
    ): Result<List<DiscussPageEntity>>

}