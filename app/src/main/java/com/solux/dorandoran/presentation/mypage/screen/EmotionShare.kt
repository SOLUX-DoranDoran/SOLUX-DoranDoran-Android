package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator


@Composable
fun EmotionShareRoute(
    navigator: MypageNavigator
) {
    EmotionShare()
}

@Composable
fun EmotionShare() {
    Text("감성 공유 화면입니다.")
}