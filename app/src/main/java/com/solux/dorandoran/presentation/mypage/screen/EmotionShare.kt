package com.solux.dorandoran.presentation.mypage.screen

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                    contentDescription = "뒤로 가기",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navigator.navController.popBackStack() }
                )

                Text(
                    text = "감성 공유",
                    style = baseBold,
                    color = Neutral60,
                    textAlign = TextAlign.Center
                )

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = "검색",
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                itemsIndexed(viewModel.emotionShareList) { index, emotion ->
                    EmotionShareListItem(
                        emotion = emotion,
                        itemIndex = index,
                        onLikeClick = {
                            viewModel.toggleLike(emotion.id)
                        }
                    )
                }
            }
        }

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