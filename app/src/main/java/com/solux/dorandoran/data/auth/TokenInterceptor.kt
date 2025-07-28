package com.solux.dorandoran.data.auth

import android.content.Context
import com.solux.dorandoran.data.auth.TokenManager
import com.solux.dorandoran.data.dto.request.ReissueRequest
import com.solux.dorandoran.data.service.AuthApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val context: Context,
    private val authApiService: AuthApiService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            TokenManager.getRefreshToken(context).first()
        } ?: return null

        return try {
            val reissueResponse = runBlocking {
                authApiService.reissueToken(ReissueRequest(refreshToken))
            }

            // 토큰 저장
            runBlocking {
                TokenManager.saveTokens(context, reissueResponse.accessToken, reissueResponse.refreshToken)
            }

            // 기존 요청에 새 accessToken으로 Authorization 헤더 추가
            response.request.newBuilder()
                .header("Authorization", "Bearer ${reissueResponse.accessToken}")
                .build()

        } catch (e: Exception) {
            null // 재발급 실패 시 null 반환 → 로그인 화면 이동
        }
    }
}