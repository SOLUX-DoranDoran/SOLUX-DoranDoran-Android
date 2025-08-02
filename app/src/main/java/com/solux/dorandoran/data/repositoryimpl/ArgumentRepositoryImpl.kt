package com.solux.dorandoran.data.repositoryimpl

import android.content.Context
import com.solux.dorandoran.data.datasource.ArgumentDataSource
import com.solux.dorandoran.data.mapper.toDiscussCommentEntity
import com.solux.dorandoran.domain.entity.DiscussCommentEntity
import com.solux.dorandoran.domain.repository.ArgumentRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ArgumentRepositoryImpl @Inject constructor(
    private val argumentDataSource: ArgumentDataSource,
    @ApplicationContext private val context: Context
) : ArgumentRepository {

    private suspend fun getAccessToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is"
    }

    override suspend fun getArgumentsForDiscussion(boardId: Int): Result<List<DiscussCommentEntity>> {
        return runCatching {
            val token = getAccessToken()
            val response = argumentDataSource.getArgumentsForDiscussion(token, boardId)
            response.map {it.toDiscussCommentEntity()}
        }
    }

    override suspend fun createArgument(boardId: Int, content: String): Result<DiscussCommentEntity> {
        return runCatching {
            val token = getAccessToken()
            val response = argumentDataSource.createArgument(token, boardId, content)
            response.toDiscussCommentEntity()
        }
    }

    override suspend fun createComment(boardId: Int, content: String, parentId: Int) : Result<DiscussCommentEntity> {
        return runCatching {
            val token = getAccessToken()
            val response = argumentDataSource.createComment(token, boardId, content, parentId)
            response.toDiscussCommentEntity()
        }
    }

}