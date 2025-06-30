package com.solux.dorandoran.presentation.mypage.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.mypage.screen.MypageRoute

fun NavGraphBuilder.mypageNavGraph(
    navigator: MypageNavigator
) {
    composable(route = "mypage") {
        MypageRoute(navigator = navigator)
    }
}