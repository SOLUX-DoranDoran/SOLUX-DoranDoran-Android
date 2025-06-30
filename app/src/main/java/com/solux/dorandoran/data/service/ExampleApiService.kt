package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.ExampleBaseResponse
import com.solux.dorandoran.data.dto.response.ResponseGetExampleDto
import com.solux.dorandoran.data.service.ApiKeyStorage.API
import com.solux.dorandoran.data.service.ApiKeyStorage.USERS
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleApiService {
    @GET("/$API/$USERS")
    suspend fun getUsers(
        @Query("page") page: Int
    ): ExampleBaseResponse<List<ResponseGetExampleDto>>
}