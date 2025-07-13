package com.solux.dorandoran.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.BookRecommendationSection
import com.solux.dorandoran.core_ui.component.CustomSearchBar
import com.solux.dorandoran.core_ui.component.EmotionShareSection
import com.solux.dorandoran.core_ui.component.HotDiscussionsSection
import com.solux.dorandoran.core_ui.component.RecentReviewsSection
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.presentation.home.navigation.HomeNavigator
import com.solux.dorandoran.presentation.home.viewmodel.HomeViewModel


@Composable
fun HomeRoute(
    navigator: HomeNavigator
) {
    HomeScreen(navigator=navigator)
}

@Composable
fun HomeScreen(
    navigator: HomeNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02),
        verticalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(vertical = 22.dp)
    ) {
        // 검색창
        item {
            CustomSearchBar(
                onSearchClick = { searchQuery ->
                    // 책 제목 검색할 수 있게 키보드 띄움
                    // viewModel.searchBooks(searchQuery)
                }
            )
        }

        // 소설 부문 추천 도서
        item {
            BookRecommendationSection(
                books = viewModel.recommendedBooks,
                onBookClick = { bookId ->
                    // RecentReview.kt로 이동
                    navigator.navigateToRecentReview()
                }
            )
        }

        // 최근 리뷰
        item {
            RecentReviewsSection(
                review = viewModel.recentReview,
                // 하나의 리뷰만 쓰도록 함수명 변경
                onReviewClick = { reviewId ->
                    navigator.navigateToReviewTotal()
                },
                onMoreClick = {
                    navigator.navigateToRecentReview()
                }
            )
        }

        // 지금 핫한 토론
        item {
            HotDiscussionsSection(
                discussion = viewModel.hotDiscussions,
                onDiscussionClick = { discussionId ->
                    // Discussing.kt로 이동
                    navigator.navigateToDiscussing()
                },
                onMoreClick = {
                    navigator.navigateToDiscussScreen()
                }
            )
        }

        // 감성 공유
        item {
            EmotionShareSection(
                emotionShare = viewModel.emotionShares,
                onEmotionClick = { emotionId ->
                    // EmotionShare.kt로 이동
                    navigator.navigateToEmotionShare()
                },
                onMoreClick = {
                    // EmotionShare.kt로 이동
                    navigator.navigateToEmotionShare()
                }
            )
        }

        // 하단 여백
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

