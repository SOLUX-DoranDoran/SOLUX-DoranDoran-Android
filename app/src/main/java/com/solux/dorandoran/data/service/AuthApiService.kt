package com.solux.dorandoran.data.service

import com.solux.dorandoran.data.dto.request.ReissueRequest
import com.solux.dorandoran.data.dto.response.ReissueResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("/api/auth/reissue")
    suspend fun reissueToken(
        @Body request: ReissueRequest
    ): ReissueResponse
}
