package com.solux.dorandoran.presentation.discuss.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.discuss.navigation.DiscussNavigator


@Composable
fun DiscussingRoute(
    navigator: DiscussNavigator
) {
    Discussing()
}

@Composable
fun Discussing() {
    Text("토론방입니다.")
}
