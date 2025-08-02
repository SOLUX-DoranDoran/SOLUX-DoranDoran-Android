package com.solux.dorandoran.data.repositoryimpl

import android.content.Context
import com.solux.dorandoran.data.datasource.DiscussDataSource
import com.solux.dorandoran.data.mapper.toDiscussPageEntity
import com.solux.dorandoran.domain.entity.DiscussPageEntity
import com.solux.dorandoran.domain.repository.DiscussRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DiscussRepositoryImpl @Inject constructor(
    private val discussDataSource: DiscussDataSource,
    @ApplicationContext private val context: Context
) : DiscussRepository {

    private suspend fun getAccessToken(): String {
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTA0NDM0Nzk3NDYyMjYwOTcxMjIiLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzU0OTk5OTUyfQ.WvghkrfFxUIWnQjwVS8OJHx_LQrnnxldh9A7nUG26is"
    }

    override suspend fun getDiscussions(page: Int, size: Int): Result<List<DiscussPageEntity>> {
        return runCatching {
            val token = getAccessToken()
            val response = discussDataSource.getDiscussions(token, page, size)
            response.map {it.toDiscussPageEntity()}
        }
    }

    override suspend fun getDiscussionsForBook(bookId: Int): Result<DiscussPageEntity> {
        return runCatching {
            val token = getAccessToken()
            val response = discussDataSource.getDiscussionsForBook(token, bookId)
            response.toDiscussPageEntity()
        }
    }

    override suspend fun createDiscussion(
        title: String,
        content: String,
        bookTitle: String
    ): Result<DiscussPageEntity> {
        return runCatching {
            val token = getAccessToken()
            val response = discussDataSource.createDiscussion(token, title, content, bookTitle)
            response.toDiscussPageEntity()
        }
    }

    override suspend fun getDiscussionDetails(boardId: Int): Result<List<DiscussPageEntity>> {
        return runCatching {
            val token = getAccessToken()
            val response = discussDataSource.getDiscussionDetails(token, boardId)
            response.map {it.toDiscussPageEntity()}
        }
    }
}