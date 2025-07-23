package com.solux.dorandoran.core_ui.component


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.domain.entity.EmotionShareEntity

@Composable
fun EmotionShareItem(
    emotion: EmotionShareEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(363.dp)
            .height(180.dp) // 159.61dp에서 180dp로 증가
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = Background01),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = 2.dp,
            color = Background01
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            Text(
                text = emotion.bookTitle,
                style = baseBold,
                color = Neutral60,
                modifier = Modifier.width(187.dp).height(23.dp)
            )

            Spacer(modifier = Modifier.height(15.dp)) // 간격 약간 축소

            // 인용문 스타일
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "\"",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 45.sp, // 크기 약간 축소
                    color = Button02,
                    modifier = Modifier.width(27.dp)
                )

                Spacer(modifier = Modifier.width(13.dp))

                Text(
                    text = emotion.content,
                    style = smallRegular,
                    color = Neutral60,
                    maxLines = 3, // 3줄로 제한
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(280.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp)) // 간격 증가

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // 프로필 이미지
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = Button02,
                            shape = CircleShape
                        )
                        .background(Background03)
                )

                Spacer(modifier = Modifier.width(10.dp))

                // 사용자명
                Text(
                    text = emotion.userName,
                    style = smallRegular,
                    color = Neutral60
                )
            }
        }
    }
}