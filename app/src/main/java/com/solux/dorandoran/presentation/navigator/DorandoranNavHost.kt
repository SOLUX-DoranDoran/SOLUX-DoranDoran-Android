package com.solux.dorandoran.presentation.navigator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.auth.navigation.AuthNavigator
import com.solux.dorandoran.presentation.auth.screen.SplashScreen
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.presentation.discuss.navigation.discussNavGraph
import com.solux.dorandoran.presentation.home.navigation.HomeNavigator
import com.solux.dorandoran.presentation.home.navigation.homeNavGraph
import com.solux.dorandoran.presentation.main.navigation.MainNavigator
import com.solux.dorandoran.presentation.main.navigation.mainNavGraph
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import com.solux.dorandoran.presentation.mypage.navigation.mypageNavGraph
import com.solux.dorandoran.presentation.writing.navigation.WritingNavigator
import com.solux.dorandoran.presentation.writing.navigation.writingNavGraph


@Composable
fun DoranDoranNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authNavigator: AuthNavigator,
    mainNavigator: MainNavigator,
    homeNavigator: HomeNavigator,
    discussNavigator: DiscussNavigator,
    writingNavigator: WritingNavigator,
    mypageNavigator: MypageNavigator
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = "splash",
        ) {
            composable("splash") { SplashScreen(navController = authNavigator.navController) }

            mainNavGraph(
                mainNavigator,
                homeNavigator,
                discussNavigator,
                writingNavigator,
                mypageNavigator
            )
            homeNavGraph(homeNavigator)
            discussNavGraph(discussNavigator)
            writingNavGraph(writingNavigator)
            mypageNavGraph(mypageNavigator)

        }
    }
}