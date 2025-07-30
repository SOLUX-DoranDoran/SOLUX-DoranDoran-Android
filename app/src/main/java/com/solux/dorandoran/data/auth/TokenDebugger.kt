package com.solux.dorandoran.data.auth

import android.content.Context
import android.util.Log
import com.solux.dorandoran.BuildConfig
import kotlinx.coroutines.flow.first

object TokenDebugUtils {

    suspend fun logStoredTokens(context: Context) {
        if (BuildConfig.DEBUG) {
            try {
                val accessToken = TokenManager.getAccessToken(context).first()
                val refreshToken = TokenManager.getRefreshToken(context).first()

                Log.d("TokenDebug", "=== 저장된 토큰 정보 ===")
                Log.d("TokenDebug", "AccessToken exists: ${!accessToken.isNullOrBlank()}")
                Log.d("TokenDebug", "RefreshToken exists: ${!refreshToken.isNullOrBlank()}")

                if (!accessToken.isNullOrBlank()) {
                    Log.d("TokenDebug", "AccessToken: $accessToken")
                }
                if (!refreshToken.isNullOrBlank()) {
                    Log.d("TokenDebug", "RefreshToken: $refreshToken")
                }

            } catch (e: Exception) {
                Log.e("TokenDebug", "토큰 확인 중 오류", e)
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
            Log.e("TokenDebug", "validateTokenStorage 중 오류 발생", e)
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