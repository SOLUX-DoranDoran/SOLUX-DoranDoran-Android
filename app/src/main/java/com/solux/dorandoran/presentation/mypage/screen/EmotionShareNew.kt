package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.*

@Composable
fun EmotionShareNewRoute(
    navigator: MypageNavigator
) {
    EmotionShareNew(navigator=navigator)
}

@Composable
fun EmotionShareNew(navigator: MypageNavigator) {
    var bookTitle by remember { mutableStateOf("") }
    var quote by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Status Bar Spacer
        Spacer(modifier = Modifier.height(22.dp))

        // Title
        Text(
            text = "감성 공유",
            style = baseBold,
            color = Neutral60,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Book Title Section
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "책 이름",
                style = baseBold,
                color = Neutral60
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Button02,
                    unfocusedBorderColor = Neutral80,
                    focusedContainerColor = Background01,
                    unfocusedContainerColor = Background01
                ),
                shape = RoundedCornerShape(15.dp),
                textStyle = baseRegular,
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Quote Section
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "명언",
                style = baseBold,
                color = Neutral60
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = quote,
                onValueChange = { newValue ->
                    // 엔터 개수로 줄 수 제한
                    val lineBreaks = newValue.count {it == '\n'}

                    if (lineBreaks < 13 && newValue.length <= 500) {
                        quote = newValue
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(310.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Button02,
                    unfocusedBorderColor = Neutral80,
                    focusedContainerColor = Background01,
                    unfocusedContainerColor = Background01
                ),
                shape = RoundedCornerShape(15.dp),
                textStyle = baseRegular,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Register Button
        Button(
            onClick = {
                // 등록 로직 구현
                navigator.navigateToEmotionShare()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(3.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Button02,
                contentColor = Background01
            )
        ) {
            Text(
                text = "등록하기",
                style = baseBold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}