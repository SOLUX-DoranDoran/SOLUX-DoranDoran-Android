package com.solux.dorandoran.presentation.example.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.example.navigation.ExampleNavigator


@Composable
fun ExampleRoute(
    navigator: ExampleNavigator
) {

    ExampleScreen()
}

@Composable
fun ExampleScreen() {
	Text("예시 화면입니다.")

 }


