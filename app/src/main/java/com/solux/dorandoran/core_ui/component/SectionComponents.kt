package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.*
import com.solux.dorandoran.domain.entity.*

/**
 * 섹션 컴포넌트들
 * - 각 섹션의 제목, 레이아웃, 더보기 버튼 등을 관리
 * - 개별 아이템 컴포넌트들을 조합하여 완성된 섹션을 구성
 */

@Composable
fun RecommendedBooksSection(
    books: List<BookEntity>,
    onBookClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // 섹션 제목
        SectionTitle(
            mainText = "소설 부문 ",
            highlightText = "추천 도서"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 책 리스트를 감싸는 테두리 박스
        RecommendedBooksContainer {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(books) { book ->
                    BookRecommendationItem(
                        book = book,
                        onClick = { onBookClick(book.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun RecentReviewsSection(
    reviews: List<ReviewEntity>,
    onReviewClick: (Long) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        // 섹션 제목 + 더보기 버튼
        SectionHeader(
            title = "최근 리뷰",
            onMoreClick = onMoreClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 리뷰 리스트 (세로 스크롤, 높이 제한)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(reviews) { review ->
                ReviewItem(
                    review = review,
                    onClick = { onReviewClick(review.id) }
                )
            }
        }
    }
}

@Composable
fun HotDiscussionsSection(
    discussions: List<DiscussionEntity>,
    onDiscussionClick: (Long) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        SectionHeader(
            title = "지금 핫한 토론",
            onMoreClick = onMoreClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(discussions) { discussion ->
                DiscussionItem(
                    discussion = discussion,
                    onClick = { onDiscussionClick(discussion.id) }
                )
            }
        }
    }
}

@Composable
fun EmotionShareSection(
    emotionShares: List<EmotionShareEntity>,
    onEmotionClick: (Long) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        SectionHeader(
            title = "감성 공유",
            onMoreClick = onMoreClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(120.dp)
        ) {
            items(emotionShares) { emotion ->
                EmotionShareItem(
                    emotion = emotion,
                    onClick = { onEmotionClick(emotion.id) }
                )
            }
        }
    }
}

/**
 * 공통 UI 컴포넌트들
 * - 섹션에서 반복적으로 사용되는 UI 요소들
 */

@Composable
private fun SectionTitle(
    mainText: String,
    highlightText: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = mainText,
            style = largeBold,
            color = Neutral60
        )
        Text(
            text = highlightText,
            style = largeBold,
            color = Button02
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = largeBold,
            color = Neutral60
        )

        TextButton(onClick = onMoreClick) {
            Text(
                text = "더보기",
                style = baseRegular,
                color = Neutral70
            )
        }
    }
}

@Composable
private fun RecommendedBooksContainer(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, Button02, RoundedCornerShape(12.dp))
            .background(Background01)
            .padding(16.dp)
    ) {
        content()
    }
}