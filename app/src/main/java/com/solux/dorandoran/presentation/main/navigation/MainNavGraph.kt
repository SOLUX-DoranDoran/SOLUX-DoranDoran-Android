package com.solux.dorandoran.presentation.main.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.presentation.home.navigation.HomeNavigator
import com.solux.dorandoran.presentation.main.screen.MainRoute
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import com.solux.dorandoran.presentation.writing.navigation.WritingNavigator

fun NavGraphBuilder.mainNavGraph(
    mainNavigator: MainNavigator,
    homeNavigator: HomeNavigator,
    discussNavigator: DiscussNavigator,
    writingNavigator: WritingNavigator,
    mypageNavigator: MypageNavigator
) {
    composable(route = "main") {
        MainRoute(navigator = mainNavigator)
    }
}