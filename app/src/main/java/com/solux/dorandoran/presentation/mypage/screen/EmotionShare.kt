package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.component.EmotionShareListItem
import com.solux.dorandoran.core_ui.theme.*
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator
import com.solux.dorandoran.presentation.mypage.viewmodel.EmotionShareViewModel

@Composable
fun EmotionShareRoute(
    navigator: MypageNavigator,
    viewModel: EmotionShareViewModel = hiltViewModel()
) {
    EmotionShare(
        navigator = navigator,
        viewModel = viewModel
    )
}

@Composable
fun EmotionShare(
    navigator: MypageNavigator,
    viewModel: EmotionShareViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 상태바 공간
            Spacer(modifier = Modifier.height(20.dp))

            // 상단 헤더
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 뒤로가기 버튼
                Image(
                    painter = painterResource(id = R.drawable.ic_back), // 뒤로가기 아이콘 필요
                    contentDescription = "뒤로가기",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navigator.navController.popBackStack() }
                )

                // 제목
                Text(
                    text = "감성 공유",
                    style = baseBold,
                    color = Neutral60,
                    textAlign = TextAlign.Center
                )

                // 검색 버튼
                Image(
                    painter = painterResource(id = R.drawable.ic_search), // 검색 아이콘 필요
                    contentDescription = "검색",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* 검색 기능 구현 */ }
                )
            }

            // 스크롤 가능한 리스트
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp), // 아이템 간 간격 줄임
                contentPadding = PaddingValues(bottom = 100.dp) // FAB를 위한 하단 패딩
            ) {
                itemsIndexed(viewModel.emotionShareList) { index, emotion ->
                    EmotionShareListItem(
                        emotion = emotion,
                        itemIndex = index,
                        onItemClick = {
                            // 상세 페이지로 이동 로직
                        },
                        onLikeClick = {
                            // 좋아요 토글 로직
                            viewModel.toggleLike(emotion.id)
                        }
                    )
                }
            }
        }

        // 플로팅 액션 버튼
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