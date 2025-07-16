package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // 메인
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("감성 공유 화면입니다.")
        }

        // navigation할 버튼
        FloatingActionButton(
            onClick = { navigator.navigateToEmotionShareNew() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 30.dp)
                .size(57.dp)
                .shadow(
                    elevation = 2.23.dp,
                    shape = CircleShape
                ),
            shape = CircleShape,
            containerColor = Button02,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "감성 공유 글 추가",
                modifier = Modifier.size(25.dp)
            )
        }
    }
}