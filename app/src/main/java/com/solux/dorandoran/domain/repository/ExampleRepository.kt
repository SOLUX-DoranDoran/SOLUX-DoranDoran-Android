package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.domain.entity.ExampleEntity

interface ExampleRepository {
    suspend fun getUsers(page: Int): Result<List<ExampleEntity>>
}