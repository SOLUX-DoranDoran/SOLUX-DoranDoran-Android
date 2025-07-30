package com.solux.dorandoran.data.datasource

import com.solux.dorandoran.data.dto.response.ReissueResponse

interface AuthRemoteDataSource {
    suspend fun reissueToken(refreshToken: String): ReissueResponse
}
