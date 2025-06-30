package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator


@Composable
fun MypageRoute(
    navigator: MypageNavigator
) {

    MypageScreen()
}

@Composable
fun MypageScreen() {
	Text("마이페이지입니다.")

 }


