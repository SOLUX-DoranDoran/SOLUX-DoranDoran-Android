package com.solux.dorandoran.presentation.main.screen

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solux.dorandoran.core_ui.theme.DoranDoranTheme
import com.solux.dorandoran.data.auth.TokenDebugUtils
import com.solux.dorandoran.data.auth.TokenManager
import com.solux.dorandoran.presentation.auth.navigation.AuthNavigator
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.presentation.home.navigation.HomeNavigator
import com.solux.dorandoran.presentation.main.navigation.MainNavigator
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import com.solux.dorandoran.presentation.navigator.DoranDoranNavHost
import com.solux.dorandoran.presentation.review.navigation.ReviewNavigator
import com.solux.dorandoran.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DoranDoranTheme {
                val context = LocalContext.current
                val systemUiController = rememberSystemUiController()
                val lifecycleOwner = LocalLifecycleOwner.current
                var backPressedTime by remember { mutableStateOf(0L) }
                var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }
                var isLoading by remember { mutableStateOf(true) }
                var loadingMessage by remember { mutableStateOf("앱을 시작하는 중...") }

                BackHandler {
                    if (System.currentTimeMillis() - backPressedTime <= 3000) {
                        (context as Activity).finish()
                    } else {
                        context.toast("한 번 더 누르면 종료돼요")
                    }
                    backPressedTime = System.currentTimeMillis()
                }

                SideEffect {
                    systemUiController.setStatusBarColor(color = White)
                }

                DisposableEffect(key1 = lifecycleOwner) {
                    onDispose {
                        systemUiController.setStatusBarColor(color = Transparent)
                    }
                }

                val navController = rememberNavController()

                val authNavigator = remember(navController) { AuthNavigator(navController) }
                val mainNavigator = remember(navController) { MainNavigator(navController) }
                val homeNavigator = remember(navController) { HomeNavigator(navController) }
                val discussNavigator = remember(navController) { DiscussNavigator(navController) }
                val reviewNavigator = remember(navController) { ReviewNavigator(navController) }
                val mypageNavigator = remember(navController) { MypageNavigator(navController) }

                // 토큰 존재 여부 확인 및 서버 연결 테스트
                LaunchedEffect(Unit) {
                    try {
                        loadingMessage = "로그인 정보 확인 중..."

                        // 디버그 코드
                        TokenDebugUtils.logStoredTokens(context)
                        val validationResult = TokenDebugUtils.validateTokenStorage(context)
                        Timber.tag("TokenValidation")
                            .d("Token validation result: $validationResult")

                        // 딥링크로 토큰이 전달된 경우
                        intent.data?.let { uri ->
                            val accessToken = uri.getQueryParameter("accessToken")
                            val refreshToken = uri.getQueryParameter("refreshToken")
                            if (!accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                                loadingMessage = "네이버 로그인 처리 중..."
                                TokenManager.saveTokens(context, accessToken, refreshToken)

                                // 서버 연결 테스트
                                loadingMessage = "서버 연결 확인 중..."
                                val serverConnectionResult = testServerConnection(accessToken)

                                if (serverConnectionResult) {
                                    isLoggedIn = true
                                    context.toast("네이버 로그인 성공!")
                                    Timber.tag("네이버로그인")
                                        .d("로그인 성공! accessToken: ${accessToken.take(20)}...")
                                } else {
                                    // 서버 연결 실패 시 토큰 삭제
                                    TokenManager.clearTokens(context)
                                    isLoggedIn = false
                                    context.toast("서버 연결에 실패했습니다. 다시 로그인해주세요.")
                                    Timber.tag("네이버로그인").w("토큰은 받았지만 서버 연결 실패")
                                }

                                isLoading = false
                                TokenDebugUtils.logStoredTokens(context)
                                return@LaunchedEffect
                            }
                        }

                        // 기존 토큰 확인
                        val existingAccessToken = TokenManager.getAccessToken(context).first()
                        val existingRefreshToken = TokenManager.getRefreshToken(context).first()

                        if (!existingAccessToken.isNullOrBlank() && !existingRefreshToken.isNullOrBlank()) {
                            // 토큰이 있는 경우 서버 연결 테스트
                            loadingMessage = "서버 연결 확인 중..."
                            val serverConnectionResult = testServerConnection(existingAccessToken)

                            if (serverConnectionResult) {
                                isLoggedIn = true
                                Timber.tag("MainActivity").d("기존 토큰으로 서버 연결 성공")
                            } else {
                                // 서버 연결 실패 시 토큰 삭제하고 로그인 화면으로
                                TokenManager.clearTokens(context)
                                isLoggedIn = false
                                Timber.tag("MainActivity").w("기존 토큰으로 서버 연결 실패 - 토큰 삭제")
                                context.toast("로그인이 만료되었습니다. 다시 로그인해주세요.")
                            }
                        } else {
                            isLoggedIn = false
                        }

                        isLoading = false
                        Timber.tag("MainActivity").d("토큰 확인 완료 - isLoggedIn: $isLoggedIn")

                    } catch (e: Exception) {
                        Timber.tag("MainActivity").e(e, "토큰 확인 중 오류 발생")
                        isLoggedIn = false
                        isLoading = false
                        context.toast("앱 시작 중 오류가 발생했습니다.")
                    }
                }

                // 로딩 중일 때는 로딩 화면 표시
                if (isLoading) {
                    LoadingScreen(message = loadingMessage)
                    return@DoranDoranTheme
                }

                val startDestination = if (isLoggedIn == true) "main" else "signin"

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    content = { paddingValues ->
                        DoranDoranNavHost(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController,
                            authNavigator = authNavigator,
                            mainNavigator = mainNavigator,
                            homeNavigator = homeNavigator,
                            discussNavigator = discussNavigator,
                            reviewNavigator = reviewNavigator,
                            mypageNavigator = mypageNavigator,
                            startDestination = startDestination
                        )
                    }
                )
            }
        }
    }

    // 서버 연결 테스트 함수 - 성공/실패 여부를 반환
    private suspend fun testServerConnection(accessToken: String): Boolean {
        return try {
            Timber.tag("ServerTest").d("서버 연결 테스트 시작...")

            val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

            val request = Request.Builder()
                .url("http://ec2-15-164-67-216.ap-northeast-2.compute.amazonaws.com:8080/api/user/profile")
                .addHeader("Authorization", "Bearer $accessToken")
                .build()

            withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                Timber.tag("ServerTest").d("응답 코드: ${response.code}")
                Timber.tag("ServerTest").d("응답 메시지: ${response.message}")

                val isSuccessful = response.isSuccessful

                if (isSuccessful) {
                    Timber.tag("ServerTest").d("✅ 서버 연결 성공!")
                    val responseBody = response.body?.string()
                    Timber.tag("ServerTest").d("응답 내용: ${responseBody?.take(100)}...")
                } else {
                    Timber.tag("ServerTest").w("⚠️ 서버 응답 오류: ${response.code}")
                    when (response.code) {
                        401 -> Timber.tag("ServerTest").w("토큰이 만료되었거나 유효하지 않음")
                        404 -> Timber.tag("ServerTest").w("API 엔드포인트를 찾을 수 없음")
                        500 -> Timber.tag("ServerTest").w("서버 내부 오류")
                        else -> Timber.tag("ServerTest").w("기타 오류")
                    }
                }
                response.close()
                isSuccessful
            }

        } catch (e: Exception) {
            Timber.tag("ServerTest").e(e, "❌ 서버 연결 실패")
            when (e) {
                is ConnectException -> Timber.tag("ServerTest").e("서버에 연결할 수 없음")
                is SocketTimeoutException -> Timber.tag("ServerTest").e("연결 시간 초과")
                else -> Timber.tag("ServerTest").e("알 수 없는 네트워크 오류: ${e.message}")
            }
            false
        }
    }
}

@Composable
private fun LoadingScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}