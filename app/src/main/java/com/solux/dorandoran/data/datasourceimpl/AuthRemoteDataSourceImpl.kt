package com.solux.dorandoran.data.datasourceimpl

import com.solux.dorandoran.data.datasource.AuthRemoteDataSource
import com.solux.dorandoran.data.dto.request.ReissueRequest
import com.solux.dorandoran.data.dto.response.ReissueResponse
import com.solux.dorandoran.data.service.AuthApiService

class AuthRemoteDataSourceImpl(
    private val authApiService: AuthApiService
) : AuthRemoteDataSource {

    override suspend fun reissueToken(refreshToken: String): ReissueResponse {
        return authApiService.reissueToken(ReissueRequest(refreshToken))
    }
}
