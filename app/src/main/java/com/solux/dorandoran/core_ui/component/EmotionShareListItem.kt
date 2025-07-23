package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.*
import com.solux.dorandoran.domain.entity.EmotionShareEntity

@Composable
fun EmotionShareListItem(
    emotion: EmotionShareEntity,
    itemIndex: Int,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 홀수 번째는 왼쪽 꼬리, 짝수 번째는 오른쪽 꼬리
    val isOddPosition = (itemIndex % 2 != 0) // 0-based index에서 1, 3, 5...가 true (오른쪽 꼬리)

    BubbleCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        isOddPosition = isOddPosition
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // 책 제목
            Text(
                text = emotion.bookTitle,
                style = baseBold,
                color = Neutral60,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 인용문
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "\"",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    color = Button02,
                    modifier = Modifier.width(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = emotion.content,
                    style = baseRegular,
                    color = Neutral60,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 하단 영역 (프로필 + 좋아요)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 프로필 영역
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, Button02, CircleShape)
                            .background(Background03)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = emotion.userName,
                        style = smallRegular,
                        color = Neutral60
                    )
                }

                // 좋아요 영역
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onLikeClick() }
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (emotion.isLiked) {
                                R.drawable.ic_emotionsharescreen_heart_fill
                            } else {
                                R.drawable.ic_emotionsharesreen_heart
                            }),
                        contentDescription = "좋아요",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified // 원본 색상 유지
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = emotion.likeCount.toString(),
                        style = smallRegular,
                        color = if (emotion.isLiked) Button02 else Neutral60
                    )
                }
            }
        }
    }
}