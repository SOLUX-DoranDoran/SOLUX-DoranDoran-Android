package com.solux.dorandoran.core_ui.component


import android.widget.Button
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.DoranDoranTheme
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral70
import com.solux.dorandoran.core_ui.theme.Neutral80
import com.solux.dorandoran.core_ui.theme.largeBold
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.core_ui.theme.smallRegular02
import com.solux.dorandoran.domain.entity.DiscussionPageEntity

@Composable
fun DiscussionRoomBox(
    discussion: DiscussionPageEntity,
    onClick: (Int)->Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(discussion.id) }
            .border(1.dp, Button02, RoundedCornerShape(12.dp)),
        shape= RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Background01),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Background03)
            )

            Spacer(modifier = Modifier.width(30.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = discussion.discussionTopic,
                    style = baseBold,
                    color = Neutral60,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = discussion.discussionArgument,
                    style = smallRegular,
                    color = Neutral70
                )

                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 프로필 이미지 (임시)
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(Background03)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = discussion.name,
                        style = smallRegular02,
                        color = Neutral70
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiscussionRoomPreview() {
    DoranDoranTheme {
        DiscussionRoomBox(
            discussion = DiscussionPageEntity(
                id = 1,
                name = "김눈송",
                bookTitle = "로미오와 줄리엣",
                discussionTopic = "둘은 찐 사랑이 맞는가",
                bookImageUrl = "",
                authorName = "셰익스피어",
                publisher = "민음사",
                publishDate = "2008년 2월 28일",
                discussionArgument = "저게 사랑이 아니면 뭐란말임"
            ),
            onClick = { },
            modifier = Modifier
        )
    }
}