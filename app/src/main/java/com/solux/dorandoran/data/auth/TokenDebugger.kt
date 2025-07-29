package com.solux.dorandoran.data.auth

import android.content.Context
import android.util.Log
import com.solux.dorandoran.BuildConfig
import kotlinx.coroutines.flow.first
import timber.log.Timber

// 디버그용 토큰 확인 함수 (개발 환경에서만 사용)
object TokenDebugUtils {

    suspend fun logStoredTokens(context: Context) {
        if (BuildConfig.DEBUG) {
            try {
                val accessToken = TokenManager.getAccessToken(context).first()
                val refreshToken = TokenManager.getRefreshToken(context).first()

                Timber.tag("TokenDebug").d("=== 저장된 토큰 정보 ===")
                Timber.tag("TokenDebug").d("AccessToken exists: ${!accessToken.isNullOrBlank()}")
                Timber.tag("TokenDebug").d("RefreshToken exists: ${!refreshToken.isNullOrBlank()}")

                if (!accessToken.isNullOrBlank()) {
                    Timber.tag("TokenDebug").d("AccessToken: ${accessToken}")
                }
                if (!refreshToken.isNullOrBlank()) {
                    Timber.tag("TokenDebug").d("RefreshToken: ${refreshToken}")
                }

            } catch (e: Exception) {
                Timber.tag("TokenDebug").e(e, "토큰 확인 중 오류")
            }
        }
    }

    suspend fun validateTokenStorage(context: Context): TokenValidationResult {
        return try {
            val accessToken = TokenManager.getAccessToken(context).first()
            val refreshToken = TokenManager.getRefreshToken(context).first()

            when {
                accessToken.isNullOrBlank() && refreshToken.isNullOrBlank() ->
                    TokenValidationResult.NO_TOKENS

                accessToken.isNullOrBlank() ->
                    TokenValidationResult.MISSING_ACCESS_TOKEN

                refreshToken.isNullOrBlank() ->
                    TokenValidationResult.MISSING_REFRESH_TOKEN

                else ->
                    TokenValidationResult.VALID
            }
        } catch (e: Exception) {
            TokenValidationResult.ERROR
        }
    }
}

enum class TokenValidationResult {
    VALID,
    NO_TOKENS,
    MISSING_ACCESS_TOKEN,
    MISSING_REFRESH_TOKEN,
    ERROR
}