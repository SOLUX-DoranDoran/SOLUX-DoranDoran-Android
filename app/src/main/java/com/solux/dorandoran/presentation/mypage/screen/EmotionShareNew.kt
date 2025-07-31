package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral80
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.baseRegular
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import com.solux.dorandoran.presentation.mypage.viewmodel.EmotionShareViewModel

@Composable
fun EmotionShareNewRoute(
    navigator: MypageNavigator,
    viewModel: EmotionShareViewModel = hiltViewModel()
) {
    EmotionShareNew(
        navigator = navigator,
        viewModel = viewModel
    )
}

@Composable
fun EmotionShareNew(navigator: MypageNavigator, viewModel: EmotionShareViewModel) {
    var bookTitle by remember { mutableStateOf("") }
    var quote by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "감성 공유",
            style = baseBold,
            color = Neutral60,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "책 이름",
            style = baseBold,
            color = Neutral60,
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "명언",
            style = baseBold,
            color = Neutral60,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = quote,
            onValueChange = { newValue ->
                val lineBreaks = newValue.count { it == '\n' }

                if (lineBreaks < 10 && newValue.length <= 500) {
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

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (quote.isNotEmpty() && bookTitle.isNotEmpty()) {
                    // 책 제목을 bookId로 매핑
                    val bookId = mapBookTitleToId(bookTitle)

                    viewModel.postQuote(
                        content = quote,
                        bookId = bookId
                    )

                    navigator.navigateToEmotionShare()
                }
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

private fun mapBookTitleToId(bookTitle: String): Long {
    return when (bookTitle.trim().lowercase()) {
        "나미야 잡화점의 기적" -> 1L
        "소년이 온다" -> 2L
        "로미오와 줄리엣" -> 3L
        "어린 왕자" -> 4L
        "위대한 개츠비" -> 5L
        else -> {
            // 새로운 책이면 서버에 먼저 등록하거나
            // 기본값 사용 (또는 에러 처리)
            1L
        }
    }
}