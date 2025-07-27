package com.solux.dorandoran.presentation.discuss.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.discuss.screen.DiscussDetailRoute
import com.solux.dorandoran.presentation.discuss.screen.DiscussingRoute

fun NavGraphBuilder.discussNavGraph(
    navigator: DiscussNavigator
) {
    composable(route = "discuss_detail") {
        DiscussDetailRoute(navigator = navigator)
    }

    composable(route = "discussing") {
        DiscussingRoute(navigator = navigator)
    }
}