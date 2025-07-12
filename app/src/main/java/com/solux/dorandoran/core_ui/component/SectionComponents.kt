package com.solux.dorandoran.core_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.solux.dorandoran.core_ui.theme.*
import com.solux.dorandoran.domain.entity.*

/**
 * 섹션 컴포넌트들
 * - 각 섹션의 제목, 레이아웃, 더보기 버튼 등을 관리
 * - 개별 아이템 컴포넌트들을 조합하여 완성된 섹션을 구성
 */

@Composable
fun BookRecommendationSection(
    books: List<BookEntity>,
    onBookClick: (BookEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 제목을 Card 위에 배치
        Box(
            modifier = Modifier
                .width(363.dp)
                .padding(bottom = 10.dp)
        ) {
            SectionTitle(
                mainText = "소설 부문 ",
                highlightText = "추천 도서",
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }

        // Card 컨테이너
        Card(
            modifier = Modifier
                .width(363.dp)
                .height(230.dp)
                .border(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Background03,
                            Button02
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                ),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Background01 // #FFFFFF
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                books.take(3).forEach { book ->
                    BookRecommendationItem(
                        book = book,
                        onClick = { onBookClick(book) }
                    )
                }
            }
        }
    }
}

@Composable
fun RecentReviewsSection(
    review: ReviewEntity?,
    onReviewClick: (Long) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // SectionHeader를 사용하여 제목과 더보기 버튼을 포함
        SectionHeader(
            title = "최근 리뷰",
            onMoreClick = onMoreClick,
            modifier = Modifier
                .width(363.dp)
                .padding(bottom = 12.dp)
        )

        // 하나의 리뷰 표시
        review?.let {
            ReviewItem(
                review = it,
                onClick = { onReviewClick(it.id) }
            )
        }
    }
}

@Composable
fun HotDiscussionsSection(
    discussion: DiscussionEntity?,
    onDiscussionClick: (Long) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // SectionHeader를 사용하여 제목과 더보기 버튼을 포함
        SectionHeader(
            title = "지금 핫한 토론",
            onMoreClick = onMoreClick,
            modifier = Modifier
                .width(363.dp)
                .padding(bottom = 12.dp)
        )

        // 디자인에 맞는 크기와 스타일의 단일 토론 표시
        discussion?.let {
            DiscussionItem(
                discussion = it,
                onClick = { onDiscussionClick(it.id) }
            )
        }
    }
}

@Composable
fun EmotionShareSection(
    emotionShare: EmotionShareEntity?,
    onEmotionClick: (Long) -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // SectionHeader를 사용하여 제목과 더보기 버튼을 포함
        SectionHeader(
            title = "감성 공유",
            onMoreClick = onMoreClick,
            modifier = Modifier
                .width(363.dp)
                .padding(bottom = 12.dp)
        )

        // 고정 크기로 하나의 감성 공유 표시
        emotionShare?.let {
            EmotionShareItem(
                emotion = it,
                onClick = { onEmotionClick(it.id) }
            )
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
            // "소설 부문 추천 도서"만 다르게
            style = if (mainText.contains("소설 부문")) largeBold else baseBold,
            color = Neutral60
        )
        Text(
            text = highlightText,
            // "소설 부문 추천 도서"만 다르게
            style = if (mainText.contains("소설 부문")) largeBold else baseBold,
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
        // 제목을 SectionTitle 형태로 분리하여 하이라이트 효과 적용
        when (title) {
            "최근 리뷰" -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "최근 ",
                        style = baseBold,
                        color = Neutral60
                    )
                    Text(
                        text = "리뷰",
                        style = baseBold,
                        color = Button02
                    )
                }
            }
            "지금 핫한 토론" -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "지금 핫한 ",
                        style = baseBold,
                        color = Neutral60
                    )
                    Text(
                        text = "토론",
                        style = baseBold,
                        color = Button02
                    )
                }
            }
            "감성 공유" -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "감성 ",
                        style = baseBold,
                        color = Neutral60
                    )
                    Text(
                        text = "공유",
                        style = baseBold,
                        color = Button02
                    )
                }
            }
            else -> {
                Text(
                    text = title,
                    style = baseBold,
                    color = Neutral60
                )
            }
        }

        TextButton(onClick = onMoreClick) {
            Text(
                text = "더보기",
                style = baseRegular,
                color = Neutral60
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