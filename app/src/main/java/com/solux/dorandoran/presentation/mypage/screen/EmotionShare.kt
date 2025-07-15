//package com.solux.dorandoran.presentation.mypage.screen
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
//
//
//@Composable
//fun EmotionShareRoute(
//    navigator: MypageNavigator
//) {
//    EmotionShare()
//}
//
//@Composable
//fun EmotionShare() {
//    Text("감성 공유 화면입니다.")
//}
package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator

@Composable
fun EmotionShareRoute(
    navigator: MypageNavigator
) {
    EmotionShare(navigator = navigator)
}

@Composable
fun EmotionShare(
    navigator: MypageNavigator
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigator.navigateToEmotionShareNew() },
                containerColor = Button02,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "감성 공유 글 추가"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("감성 공유 화면입니다.")
        }
    }
}