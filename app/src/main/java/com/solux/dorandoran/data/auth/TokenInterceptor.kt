package com.solux.dorandoran.data.auth

import android.content.Context
import android.util.Log
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

        if (response.request.header("Authorization") == null) {
            Log.d("TokenAuthenticator", "Authorization 헤더가 없음")
            return null
        }

        val refreshToken = runBlocking {
            TokenManager.getRefreshToken(context).first()
        }

        if (refreshToken.isNullOrBlank()) {
            Log.d("TokenAuthenticator", "리프레시 토큰 없음 - 토큰 초기화")

            runBlocking {
                TokenManager.clearTokens(context)
            }
            return null
        }

        return try {
            val reissueResponse = runBlocking {
                authApiService.reissueToken(ReissueRequest(refreshToken))
            }

            Log.d("TokenAuthenticator", "토큰 재발급 성공 - AccessToken: ${reissueResponse.accessToken}")

            runBlocking {
                TokenManager.saveTokens(
                    context,
                    reissueResponse.accessToken,
                    reissueResponse.refreshToken
                )
            }

            response.request.newBuilder()
                .header("Authorization", "Bearer ${reissueResponse.accessToken}")
                .build()

        } catch (e: Exception) {
            Log.e("TokenAuthenticator", "토큰 재발급 실패", e)

            runBlocking {
                TokenManager.clearTokens(context)
            }
            null
        }
    }
}
