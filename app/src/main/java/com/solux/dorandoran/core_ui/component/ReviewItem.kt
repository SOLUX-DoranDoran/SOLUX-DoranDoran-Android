package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral70
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.domain.entity.ReviewEntity

@Composable
fun ReviewItem(
    review: ReviewEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(363.dp)
            .height(202.dp)
            .clickable { onClick() }
            .border(
                width = 2.dp,
                // 테두리 색 변경
                color = Background01,
                shape = RoundedCornerShape(15.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Background01),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            // 책 표지 (색상 상자로 대체)
            Box(
                modifier = Modifier
                    .width(107.dp)
                    .height(155.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Background03)
            )

            Spacer(modifier = Modifier.width(18.dp))

            // 리뷰 정보
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = review.bookTitle,
                    style = baseBold,
                    color = Neutral60
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    repeat(5) { index ->
                        Image(
                            painter = painterResource(
                                id = if (index < review.rating) {
                                    R.drawable.ic_home_star_fill  // 채워진 별
                                } else {
                                    R.drawable.ic_home_star       // 빈 별
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Text(
                    text = review.content,
                    style = smallRegular,
                    color = Neutral70,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 프로필 이미지 (임시)
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Background03)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = review.nickname,
                        style = smallRegular,
                        color = Neutral70
                    )
                }
            }
        }
    }
}