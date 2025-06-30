package com.solux.dorandoran.data.datasourceimpl

import com.solux.dorandoran.data.datasource.ExampleDataSource
import com.solux.dorandoran.data.dto.ExampleBaseResponse
import com.solux.dorandoran.data.dto.response.ResponseGetExampleDto
import com.solux.dorandoran.data.service.ExampleApiService
import javax.inject.Inject

class ExampleDataSourceImpl @Inject constructor(
    private val exampleApiService: ExampleApiService
) : ExampleDataSource {
    override suspend fun getUsers(page: Int): ExampleBaseResponse<List<ResponseGetExampleDto>> {
        return exampleApiService.getUsers(page)
    }
}