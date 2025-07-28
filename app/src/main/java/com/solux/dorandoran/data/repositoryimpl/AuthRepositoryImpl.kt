package com.solux.dorandoran.data.repositoryimpl

import com.solux.dorandoran.data.datasource.AuthRemoteDataSource
import com.solux.dorandoran.data.dto.response.ReissueResponse
import com.solux.dorandoran.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun reissueToken(refreshToken: String): ReissueResponse {
        return remoteDataSource.reissueToken(refreshToken)
    }
}
