package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.baseRegular
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.domain.entity.EmotionShareEntity

@Composable
fun EmotionShareListItem(
    emotion: EmotionShareEntity,
    itemIndex: Int,
    onLikeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isOddPosition = (itemIndex % 2 != 0)

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
            Text(
                text = emotion.bookTitle,
                style = baseBold,
                color = Neutral60,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
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