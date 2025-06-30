package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.ExampleBaseResponse
import com.solux.dorandoran.data.dto.response.ResponseGetExampleDto

interface ExampleDataSource {
    suspend fun getUsers(page: Int): ExampleBaseResponse<List<ResponseGetExampleDto>>
}