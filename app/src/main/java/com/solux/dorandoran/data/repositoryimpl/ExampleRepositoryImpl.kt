package com.solux.dorandoran.data.repositoryimpl

import com.solux.dorandoran.data.datasource.ExampleDataSource
import com.solux.dorandoran.data.mapper.toExampleEntity
import com.solux.dorandoran.domain.entity.ExampleEntity
import com.solux.dorandoran.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) : ExampleRepository {
    override suspend fun getUsers(page: Int): Result<List<ExampleEntity>> {
        return runCatching {
            exampleDataSource.getUsers(page).data?.map { it.toExampleEntity() } ?: emptyList()
        }
    }
}