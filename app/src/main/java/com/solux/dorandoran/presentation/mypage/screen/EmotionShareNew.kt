package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator


@Composable
fun EmotionShareNewRoute(
    navigator: MypageNavigator
) {
    EmotionShareNew()
}

@Composable
fun EmotionShareNew() {
    Text("감성 글귀 추가 화면입니다.")
}