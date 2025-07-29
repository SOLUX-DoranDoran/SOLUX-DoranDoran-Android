package com.solux.dorandoran.data.auth

import android.content.Context
import com.solux.dorandoran.data.dto.request.ReissueRequest
import com.solux.dorandoran.data.service.AuthApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber

class TokenAuthenticator(
    private val context: Context,
    private val authApiService: AuthApiService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.request.header("Authorization") == null) {
            return null
        }

        val refreshToken = runBlocking {
            TokenManager.getRefreshToken(context).first()
        }

        if (refreshToken.isNullOrBlank()) {

            runBlocking {
                TokenManager.clearTokens(context)
            }
            return null
        }

        return try {
            val reissueResponse = runBlocking {
                authApiService.reissueToken(ReissueRequest(refreshToken))
            }

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
            Timber.tag("TokenAuthenticator").e(e, "토큰 재발급 실패")

            runBlocking {
                TokenManager.clearTokens(context)
            }
            null
        }
    }
}