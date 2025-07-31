package com.solux.dorandoran.presentation.review.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.R
import com.solux.dorandoran.core_ui.component.ReviewItem
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.core_ui.theme.Button02
import com.solux.dorandoran.core_ui.theme.Neutral60
import com.solux.dorandoran.core_ui.theme.Neutral80
import com.solux.dorandoran.core_ui.theme.baseBold
import com.solux.dorandoran.core_ui.theme.baseRegular
import com.solux.dorandoran.domain.entity.ReviewEntity
import com.solux.dorandoran.domain.entity.ReviewListEntity
import com.solux.dorandoran.presentation.review.navigation.ReviewNavigator
import com.solux.dorandoran.presentation.review.viewmodel.RecentReviewViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun RecentReviewRoute(
    navigator: ReviewNavigator,
    viewModel: RecentReviewViewModel = hiltViewModel()
) {
    RecentReviewScreen(
        navigator = navigator,
        viewModel = viewModel
    )
}

@Composable
fun RecentReviewScreen(
    navigator: ReviewNavigator,
    viewModel: RecentReviewViewModel
) {
    val recentReviews by viewModel.recentReviews
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02)
    ) {
        // 헤더
        RecentReviewHeader(
            onBackClick = { navigator.navController.popBackStack() }
        )

        // 컨텐츠 영역
        when {
            isLoading -> {
                LoadingState()
            }
            errorMessage != null -> {
                ErrorState(
                    message = errorMessage!!,
                    onRetry = {
                        viewModel.clearErrorMessage()
                        viewModel.refreshReviews()
                    }
                )
            }
            recentReviews.isEmpty() -> {
                EmptyReviewState()
            }
            else -> {
                ReviewListContent(
                    reviews = recentReviews,
                    onReviewClick = { reviewListEntity ->
                        // 해당 책의 리뷰 상세 페이지로 이동
                        // bookId는 API에서 받아와야 하지만, 현재는 더미로 1L 사용
                        navigator.navigateToReviewDetail(1L)
                    }
                )
            }
        }
    }
}

@Composable
private fun RecentReviewHeader(
    onBackClick: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = "뒤로가기",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )

            Text(
                text = "최근 리뷰",
                style = baseBold,
                color = Neutral60,
                textAlign = TextAlign.Center
            )

            // 검색 아이콘 자리 (필요시 추가)
            Spacer(modifier = Modifier.size(24.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Button02,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                style = baseRegular,
                color = Neutral80,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "다시 시도",
                style = baseBold,
                color = Button02,
                modifier = Modifier.clickable { onRetry() }
            )
        }
    }
}

@Composable
private fun EmptyReviewState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "아직 등록된 리뷰가 없어요",
                style = baseBold,
                color = Neutral80
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "첫 번째 리뷰를 작성해보세요!",
                style = baseRegular,
                color = Neutral80
            )
        }
    }
}

@Composable
private fun ReviewListContent(
    reviews: List<ReviewListEntity>,
    onReviewClick: (ReviewListEntity) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(reviews) { reviewListEntity ->
            ReviewItem(
                review = convertToReviewEntity(reviewListEntity),
                onClick = { onReviewClick(reviewListEntity) }
            )
        }
    }
}

// ReviewListEntity를 기존 ReviewEntity로 변환하는 헬퍼 함수
private fun convertToReviewEntity(reviewListEntity: ReviewListEntity): ReviewEntity {
    return ReviewEntity(
        id = reviewListEntity.id,
        bookTitle = reviewListEntity.bookTitle,
        coverImageUrl = reviewListEntity.coverImageUrl,
        content = reviewListEntity.content,
        rating = reviewListEntity.rating,
        createdAt = formatDateTime(reviewListEntity.createdAt),
        nickname = reviewListEntity.nickname,
        profileImage = reviewListEntity.profileImage ?: "",
        bookId = 1L, // API에서 받아와야 할 bookId (현재는 더미)
        userId = 1L, // API에서 받아와야 할 userId (현재는 더미)
        likeCount = 0, // 좋아요 수는 별도 API로 받아올 수 있음
        commentCount = 0, // 댓글 수도 별도 API로 받아올 수 있음
        isLiked = false // 현재 사용자의 좋아요 여부
    )
}

// 날짜 포맷팅 함수
private fun formatDateTime(dateTimeString: String): String {
    return try {
        val dateTime = LocalDateTime.parse(dateTimeString.substring(0, 19))
        val formatter = DateTimeFormatter.ofPattern("MM-dd HH:mm")
        dateTime.format(formatter)
    } catch (e: Exception) {
        dateTimeString
    }
}