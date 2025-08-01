package com.solux.dorandoran.presentation.review.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.solux.dorandoran.presentation.review.screen.RecentReviewRoute
import com.solux.dorandoran.presentation.review.screen.ReviewDetailRoute

fun NavGraphBuilder.reviewNavGraph(
    navigator: ReviewNavigator
) {
    composable(route = "recent_review") {
        RecentReviewRoute(navigator = navigator)
    }

    composable(route = "review_detail/{bookId}", arguments = listOf(
        navArgument("bookId") { type = NavType.LongType }
    )) { backStackEntry ->
        val bookId = backStackEntry.arguments?.getString("bookId")?. toLongOrNull() ?: 1L
        ReviewDetailRoute(
            navigator = navigator,
            bookId = bookId
        )
    }
}