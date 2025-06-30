package com.solux.dorandoran.presentation.discuss.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator


@Composable
fun DiscussRoute(
    navigator: DiscussNavigator
) {
    DiscussScreen()
}

@Composable
fun DiscussScreen() {
	Text("토론 화면입니다.")

 }


