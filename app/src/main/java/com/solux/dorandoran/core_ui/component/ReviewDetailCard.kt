package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.theme.Background01
import com.solux.dorandoran.core_ui.theme.Background03
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral70
import com.solux.dorandoran.core_ui.theme.baseRegular
import com.solux.dorandoran.core_ui.theme.smallBold
import com.solux.dorandoran.core_ui.theme.smallRegular
import com.solux.dorandoran.core_ui.theme.smallRegular02
import com.solux.dorandoran.domain.entity.ReviewCommentEntity
import com.solux.dorandoran.domain.entity.ReviewDetailEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ReviewDetailCard(
    review: ReviewDetailEntity,
    onLikeClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onToggleComments: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // 리뷰 메인 내용
        ReviewMainContent(
            review = review,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onToggleComments = onToggleComments
        )

        // 댓글 섹션
        if (review.isCommentsVisible && review.comments.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            CommentsSection(comments = review.comments)
        }
    }
}

@Composable
private fun ReviewMainContent(
    review: ReviewDetailEntity,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onToggleComments: () -> Unit
) {
    Column {
        // 사용자 정보
        UserInfoRow(
            nickname = review.nickname,
            profileImage = review.profileImage,
            createdAt = formatDateTime(review.createdAt)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 별점
        RatingRow(rating = review.rating)

        Spacer(modifier = Modifier.height(12.dp))

        // 리뷰 내용
        Text(
            text = review.content,
            style = baseRegular,
            color = Neutral60,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 액션 버튼들 (좋아요, 댓글)
        ReviewActionRow(
            review = review,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onToggleComments = onToggleComments
        )
    }
}

@Composable
private fun UserInfoRow(
    nickname: String,
    profileImage: String?,
    createdAt: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 프로필 이미지
        if (profileImage != null) {
            AsyncImage(
                model = profileImage,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Background03)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = nickname,
                style = smallBold,
                color = Neutral60
            )

            Text(
                text = createdAt,
                style = smallRegular02,
                color = Neutral70
            )
        }
    }
}

@Composable
private fun RatingRow(
    rating: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            Image(
                painter = painterResource(
                    id = if (index < rating) {
                        R.drawable.ic_star_filled
                    } else {
                        R.drawable.ic_star
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = rating.toString(),
            style = smallBold,
            color = Neutral60
        )
    }
}

@Composable
private fun ReviewActionRow(
    review: ReviewDetailEntity,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onToggleComments: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 댓글 토글 버튼 (댓글이 있을 때만)
        if (review.comments.isNotEmpty()) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (review.isCommentsVisible) {
                        R.drawable.ic_back // 접기 아이콘
                    } else {
                        R.drawable.ic_back // 펼치기 아이콘
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onToggleComments() },
                tint = Neutral70
            )
        } else {
            Spacer(modifier = Modifier.width(20.dp))
        }

        // 좋아요, 댓글 버튼
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 좋아요 버튼
            ActionButton(
                count = review.likeCount,
                isSelected = review.isLiked,
                onClick = onLikeClick,
                iconRes = R.drawable.ic_emotionsharescreen_heart_fill
            )

            // 댓글 버튼
            ActionButton(
                count = review.commentCount,
                isSelected = false,
                onClick = onCommentClick,
                iconRes = R.drawable.ic_reviewscreen_comment
            )
        }
    }
}

@Composable
private fun ActionButton(
    count: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    iconRes: Int
) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (isSelected) Button02 else Background03)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = if (isSelected) Background01 else Neutral60
        )

        Text(
            text = count.toString(),
            style = smallRegular,
            color = if (isSelected) Background01 else Neutral60
        )
    }
}

@Composable
private fun CommentsSection(
    comments: List<ReviewCommentEntity>
) {
    Column {
        comments.forEach { comment ->
            CommentItem(comment = comment)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CommentItem(
    comment: ReviewCommentEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
    ) {
        // 댓글 내용
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "ㄴ",
                style = smallRegular,
                color = Neutral70,
                modifier = Modifier.padding(end = 8.dp)
            )

            Text(
                text = "@${comment.nickname}",
                style = smallBold,
                color = Neutral60
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = comment.content,
                style = smallRegular,
                color = Neutral60,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // 댓글 작성자 정보
        Row(
            modifier = Modifier.padding(start = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 댓글 작성자 프로필
            if (comment.profileImage != null) {
                AsyncImage(
                    model = comment.profileImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Background03)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = comment.nickname,
                    style = smallRegular02,
                    color = Neutral70
                )

                Text(
                    text = formatDateTime(comment.createdAt),
                    style = smallRegular02,
                    color = Neutral70
                )
            }
        }
    }
}

// 날짜 시간 포맷팅 함수
private fun formatDateTime(dateTimeString: String): String {
    return try {
        if (dateTimeString == "방금 전") {
            dateTimeString
        } else {
            val dateTime = LocalDateTime.parse(dateTimeString.substring(0, 19))
            val formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm")
            dateTime.format(formatter)
        }
    } catch (e: Exception) {
        dateTimeString
    }
}