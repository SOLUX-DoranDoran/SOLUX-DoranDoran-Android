package com.solux.dorandoran.presentation.writing.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.writing.navigation.WritingNavigator


@Composable
fun WritingRoute(
    navigator: WritingNavigator
) {

    WritingScreen()
}

@Composable
fun WritingScreen() {
    Text("글쓰기 페이지입니다.")

}


