package com.solux.dorandoran.presentation.review.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.review.navigation.ReviewNavigator

@Composable
fun ReviewTotalRoute(
    navigator: ReviewNavigator
) {
    ReviewTotalScreen()
}

@Composable
fun ReviewTotalScreen() {
    Text("전체 리뷰 페이지입니다.")
}