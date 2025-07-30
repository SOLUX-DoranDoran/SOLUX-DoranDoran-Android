package com.solux.dorandoran.domain.repository

import com.solux.dorandoran.data.dto.response.ReissueResponse

interface AuthRepository {
    suspend fun reissueToken(refreshToken: String): ReissueResponse
}
