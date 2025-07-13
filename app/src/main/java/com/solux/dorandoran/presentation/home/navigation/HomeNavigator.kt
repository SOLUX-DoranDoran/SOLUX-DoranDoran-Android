package com.solux.dorandoran.presentation.home.navigation

import androidx.navigation.NavController

class HomeNavigator(
    val navController: NavController
){
    // 최근 리뷰 화면으로 이동
    fun navigateToRecentReview() {
        navController.navigate("recent_review")
    }

    // 전체 리뷰 화면으로 이동
    fun navigateToReviewTotal() {
        navController.navigate("review_total")
    }

    // 전체 토론 화면으로 이동
    fun navigateToDiscussScreen() {
        navController.navigate("discuss_screen")
    }

    // 토론 중 화면으로 이동
    fun navigateToDiscussing() {
        navController.navigate("discussing")
    }

    // 감성 공유 화면으로 이동
    fun navigateToEmotionShare() {
        navController.navigate("emotion_share")
    }
}