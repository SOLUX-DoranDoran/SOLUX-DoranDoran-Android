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
import com.solux.dorandoran.domain.entity.DiscussionPageEntity

@Composable
fun DiscussionBookBox(
    discussion: DiscussionPageEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape= RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, Button02, RoundedCornerShape(12.dp))
            //그라데이션 테두리를 지원하지 않는다고 해서 비슷한 색상으로 넣어놓음
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Background01),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(15.dp))

            // 책 이미지 (임시)
            Box(
                modifier = Modifier
                    .size(110.dp, 150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Background03)
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = discussion.bookTitle,
                    style = largeBold,
                    color = Neutral60,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = discussion.authorName,
                    style = baseBold,
                    color = Neutral70
                )

                Spacer(modifier = Modifier.height(45.dp))

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                        text = discussion.publisher,
                        style = smallRegular,
                        color = Neutral80
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                        text = discussion.publishDate,
                        style = smallRegular,
                        color = Neutral80
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiscussBookPreview() {
    DoranDoranTheme {
        DiscussionBookBox(
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