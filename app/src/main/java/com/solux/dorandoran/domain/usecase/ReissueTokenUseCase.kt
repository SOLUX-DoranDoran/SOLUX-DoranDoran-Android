package com.solux.dorandoran.domain.usecase

import com.solux.dorandoran.data.dto.response.ReissueResponse
import com.solux.dorandoran.domain.repository.AuthRepository

class ReissueTokenUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(refreshToken: String): ReissueResponse {
        return repository.reissueToken(refreshToken)
    }
}
