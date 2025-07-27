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
    HomeScreen(navigator = navigator)
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
        item {
            CustomSearchBar(
                onSearchClick = { searchQuery ->
                }
            )
        }

        item {
            BookRecommendationSection(
                books = viewModel.recommendedBooks,
                onBookClick = { bookId ->
                    navigator.navigateToRecentReview()
                }
            )
        }

        item {
            RecentReviewsSection(
                review = viewModel.recentReview,
                onReviewClick = { reviewId ->
                    navigator.navigateToReviewTotal()
                },
                onMoreClick = {
                    navigator.navigateToRecentReview()
                }
            )
        }

        item {
            HotDiscussionsSection(
                discussion = viewModel.hotDiscussions,
                onDiscussionClick = { discussionId ->
                    navigator.navigateToDiscussing()
                },
                onMoreClick = {
                    navigator.navigateToDiscussDetail()
                }
            )
        }

        item {
            EmotionShareSection(
                emotionShare = viewModel.emotionShares,
                onEmotionClick = { emotionId ->
                    navigator.navigateToEmotionShare()
                },
                onMoreClick = {
                    navigator.navigateToEmotionShare()
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

