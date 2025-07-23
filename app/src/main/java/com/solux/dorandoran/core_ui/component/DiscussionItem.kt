package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral70
import com.solux.dorandoran.core_ui.theme.Neutral80
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallRegular02
import com.solux.dorandoran.domain.entity.DiscussionEntity

@Composable
fun DiscussionItem(
    discussion: DiscussionEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .widthIn(max = 363.dp) // 최대 너비 설정
            .heightIn(max = 202.dp) // 최대 높이 설정
            .fillMaxWidth() // 사용 가능한 최대 너비 사용
            .clickable { onClick() },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Background01
        ),
        border = BorderStroke(2.dp, Background01),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 21.dp),
            verticalAlignment = Alignment.Top
        ) {
            // 책 이미지 - 비율을 유지하면서 반응형으로 조정
            Box(
                modifier = Modifier
                    .weight(0.36f) // 전체 너비의 약 45% 사용
                    .aspectRatio(0.71f) // 110:155 비율 유지 (110/155 ≈ 0.71)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Background03)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // 오른쪽 콘텐츠 영역
            Column(
                modifier = Modifier
                    .weight(0.64f) // 전체 너비의 약 55% 사용
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 상단: 토론 제목 - 텍스트 영역을 동적으로 조정
                Text(
                    text = discussion.title,
                    style = baseBold,
                    color = Neutral60,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth() // 사용 가능한 전체 너비 사용
                        .weight(1f) // 남은 공간을 유연하게 사용
                )

                // 하단 영역
                Column {
                    // 책 제목
                    Text(
                        text = discussion.bookTitle,
                        style = smallRegular02,
                        color = Neutral70,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // 작성자 정보
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // 프로필 이미지
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .border(
                                    width = 0.68.dp,
                                    color = Neutral80,
                                    shape = CircleShape
                                )
                                .clip(CircleShape)
                                .background(Background03)
                        )

                        Spacer(modifier = Modifier.width(13.dp))

                        // 작성자 이름
                        Text(
                            text = "${discussion.author} 님",
                            style = smallRegular02,
                            color = Neutral70,
                            modifier = Modifier.weight(1f) // 남은 공간을 모두 사용
                        )
                    }
                }
            }
        }
    }
}