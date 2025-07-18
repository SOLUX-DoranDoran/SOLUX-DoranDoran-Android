package com.solux.dorandoran.presentation.discuss.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.solux.dorandoran.presentation.discuss.screen.DiscussRoute
import com.solux.dorandoran.presentation.discuss.screen.DiscussionRoomRoute
import androidx.navigation.NavHost as NavHost

fun NavGraphBuilder.discussNavGraph(
    navigator: DiscussNavigator
) {
    composable(route = "discuss") {
        DiscussRoute(navigator = navigator)
    }

    composable(
        route = "discussionRoom/{discussionId}",
        arguments = listOf(navArgument("discussionId") {type = NavType.IntType})
    ) { backStackEntry ->
        val discussionId = backStackEntry.arguments?.getInt("discussionId")?:0
        DiscussionRoomRoute(
            navigator = navigator,
            discussionId = discussionId
        )
    }
}