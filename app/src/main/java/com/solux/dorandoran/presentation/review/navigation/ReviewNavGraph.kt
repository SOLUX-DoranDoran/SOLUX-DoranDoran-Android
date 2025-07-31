package com.solux.dorandoran.presentation.review.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.review.screen.RecentReviewRoute
import com.solux.dorandoran.presentation.review.screen.ReviewDetailRoute

fun NavGraphBuilder.reviewNavGraph(
    navigator: ReviewNavigator
) {
    composable(route = "review") {
        ReviewDetailRoute(navigator = navigator)
    }

    composable(route = "recent_review") {
        RecentReviewRoute(navigator = navigator)
    }

    composable(route = "review_total") {
        ReviewDetailRoute(navigator = navigator)
    }

    composable(route = "review_detail/{bookId}") { backStackEntry ->
        val bookId = backStackEntry.arguments?.getString("bookId")?.toLongOrNull() ?: 1L
        ReviewDetailRoute(
            navigator = navigator,
            bookId = bookId
        )
    }
}