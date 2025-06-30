package com.solux.dorandoran.presentation.discuss.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.discuss.screen.DiscussRoute

fun NavGraphBuilder.discussNavGraph(
    navigator: DiscussNavigator
) {
    composable(route = "discuss") {
        DiscussRoute(navigator = navigator)
    }
}