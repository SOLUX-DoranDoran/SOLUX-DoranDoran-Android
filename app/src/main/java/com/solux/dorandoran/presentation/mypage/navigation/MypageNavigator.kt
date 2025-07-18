package com.solux.dorandoran.presentation.mypage.navigation

import androidx.navigation.NavController

class MypageNavigator(
    val navController: NavController
){
    // 새로운 감성 공유 작성 화면으로 이동
    fun navigateToEmotionShareNew() {
        navController.navigate("emotion_share_new")
    }

    // 감성 공유 작성 화면으로 이동
    fun navigateToEmotionShare() {
        navController.navigate("emotion_share")
    }
}