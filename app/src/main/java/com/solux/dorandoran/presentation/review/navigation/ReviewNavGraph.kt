package com.solux.dorandoran.presentation.review.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.solux.dorandoran.presentation.review.screen.RecentReviewRoute
import com.solux.dorandoran.presentation.review.screen.ReviewTotalRoute

fun NavGraphBuilder.reviewNavGraph(
    navigator: ReviewNavigator
) {
    composable(route = "review") {
        ReviewTotalRoute(navigator = navigator)
    }

    composable(route = "recent_review") {
        RecentReviewRoute(navigator = navigator)
    }

    composable(route = "review_total") {
        ReviewTotalRoute(navigator = navigator)
    }
}