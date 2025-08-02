package com.solux.dorandoran.presentation.mypage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallBold
import com.solux.dorandoran.presentation.mypage.navigation.MypageNavigator


@Composable
fun MypageRoute(
    navigator: MypageNavigator
) {
    MypageScreen()
}

@Composable
fun MypageScreen(
    nickname: String = "송이" // 액세스 토큰으로 받아온 사용자 닉네임
) {
    val tabTitles = listOf("내 리뷰 보기", "내 감성글귀 보기")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        // 상단 사용자 정보 영역
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Button02)
                .padding(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 프로필 이미지 (임시)
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Background01)
                )

                Column {
                    Text(
                        text = nickname,
                        style = baseBold,
                        color = Background02

                    )
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .size(20.dp)
                            .background(Background02)
                    ) {
                        Text(
                            text = "안녕하세요!",
                            style = smallBold,
                            color = Button02
                        )
                    }
                }
            }

            // 우측 상단 설정 버튼 (임시)
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "설정",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp),
                tint = Background01
            )
        }

        // 탭 박스
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Background01)
                .padding(top = 16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { selectedTabIndex = index },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTabIndex == index) Color.Black else Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        if (selectedTabIndex == index) {
                            Box(
                                modifier = Modifier
                                    .height(2.dp)
                                    .width(40.dp)
                                    .background(Color.Black)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(2.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 실제 내용은 나중에 탭에 맞춰 구현하면 됨
            when (selectedTabIndex) {
                0 -> {
                    Text("내 리뷰 목록", modifier = Modifier.padding(16.dp))
                }
                1 -> {
                    Text("내 감성글귀 목록", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
