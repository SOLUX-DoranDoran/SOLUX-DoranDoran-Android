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
        // 소설 부문 추천 도서와 최근 리뷰 사이가 25.dp
        // 그냥 모든 섹션 사이는 22.dp
        contentPadding = PaddingValues(vertical = 22.dp)
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
            BookRecommendationSection(
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
                    // <책 리뷰>으로 이동
                },
                onMoreClick = {
                    // <최근 리뷰>으로 이동
                }
            )
        }

        // 지금 핫한 토론
        item {
            HotDiscussionsSection(
                discussions = viewModel.hotDiscussions,
                onDiscussionClick = { discussionId ->
                    // <토론 화면>으로 이동
                },
                onMoreClick = {
                    // <토론>으로 이동
                }
            )
        }

        // 감성 공유
        item {
            EmotionShareSection(
                emotionShares = viewModel.emotionShares,
                onEmotionClick = { emotionId ->
                    // 감성 공유 상세 화면으로 이동
                    // 감성 공유 상세 화면이 없다...
                },
                onMoreClick = {
                    // 감성 공유 전체 화면으로 이동
                }
            )
        }

        // 하단 여백
        item {
            Spacer(modifier = Modifier.height(40.dp)) // 하단바 공간 확보
            // 임의로 변경. 80.dp는 너무 크다
        }
    }
}

