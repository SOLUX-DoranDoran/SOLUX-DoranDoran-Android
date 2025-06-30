package com.solux.dorandoran.presentation.writing.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import com.solux.dorandoran.presentation.mypage.screen.MypageRoute
import com.solux.dorandoran.presentation.writing.screen.WritingRoute

fun NavGraphBuilder.writingNavGraph(
    navigator: WritingNavigator
) {
    composable(route = "writing") {
        WritingRoute(navigator = navigator)
    }
}