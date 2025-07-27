package com.solux.dorandoran.presentation.review.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.review.navigation.ReviewNavigator

@Composable
fun RecentReviewRoute(
    navigator: ReviewNavigator
) {
    RecentReviewScreen()
}

@Composable
fun RecentReviewScreen() {
    Text("최근 리뷰 페이지입니다.")
}