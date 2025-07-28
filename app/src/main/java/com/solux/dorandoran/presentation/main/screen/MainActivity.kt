package com.solux.dorandoran.presentation.main.screen

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.solux.dorandoran.core_ui.theme.DoranDoranTheme
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
                var isLoggedIn by remember { mutableStateOf(false) }

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

                LaunchedEffect(Unit) {
                    intent.data?.let { uri ->
                        val accessToken = uri.getQueryParameter("accessToken")
                        val refreshToken = uri.getQueryParameter("refreshToken")
                        if (!accessToken.isNullOrBlank() && !refreshToken.isNullOrBlank()) {
                            TokenManager.saveTokens(context, accessToken, refreshToken)
                            isLoggedIn = true
                            context.toast("네이버 로그인 성공! accessToken: $accessToken")
                            navController.navigate("main") {
                                popUpTo("signin") { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    }
                }

                val startDestination = if (isLoggedIn) "main" else "signin"

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
}