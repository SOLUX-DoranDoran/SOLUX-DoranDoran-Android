package com.solux.dorandoran.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.CustomSearchBar
import com.solux.dorandoran.core_ui.component.EmotionShareSection
import com.solux.dorandoran.core_ui.component.HotDiscussionsSection
import com.solux.dorandoran.core_ui.component.RecentReviewsSection
import com.solux.dorandoran.core_ui.component.RecommendedBooksSection
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
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // 검색창
        item {
            CustomSearchBar(
                onSearchClick = {
                    // 검색 화면으로 이동
                }
            )
        }

        // 소설 부문 추천 도서
        item {
            RecommendedBooksSection(
                books = viewModel.recommendedBooks,
                onBookClick = { bookId ->
                    // 책 상세 화면으로 이동
                    // navigator.
                }
            )
        }

        // 최근 리뷰
        item {
            RecentReviewsSection(
                reviews = viewModel.recentReviews,
                onReviewClick = { reviewId ->
                    // 리뷰 상세 화면으로 이동
                },
                onMoreClick = {
                    // 리뷰 전체 화면으로 이동
                }
            )
        }

        // 지금 핫한 토론
        item {
            HotDiscussionsSection(
                discussions = viewModel.hotDiscussions,
                onDiscussionClick = { discussionId ->
                    // 토론 상세 화면으로 이동
                },
                onMoreClick = {
                    // 토론 전체 화면으로 이동
                }
            )
        }

        // 감성 공유
        item {
            EmotionShareSection(
                emotionShares = viewModel.emotionShares,
                onEmotionClick = { emotionId ->
                    // 감성 공유 상세 화면으로 이동
                },
                onMoreClick = {
                    // 감성 공유 전체 화면으로 이동
                }
            )
        }

        // 하단 여백
        item {
            Spacer(modifier = Modifier.height(80.dp)) // 하단바 공간 확보
        }
    }
}

