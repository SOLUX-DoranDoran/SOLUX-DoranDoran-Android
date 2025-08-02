package com.solux.dorandoran.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.solux.dorandoran.core_ui.component.BookRecommendationSection
import com.solux.dorandoran.core_ui.component.CustomSearchBar
import com.solux.dorandoran.core_ui.component.EmotionShareSection
import com.solux.dorandoran.core_ui.component.HotDiscussionsSection
import com.solux.dorandoran.core_ui.component.RecentReviewSection
import com.solux.dorandoran.core_ui.theme.Background02
import com.solux.dorandoran.presentation.home.navigation.HomeNavigator
import com.solux.dorandoran.presentation.home.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.material3.CircularProgressIndicator

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
    val recommendedBooks by viewModel.recommendedBooks
    val recentReviewList by viewModel.recentReviewList
    val recentEmotionShare by viewModel.recentEmotionShare
    val isLoading by viewModel.isLoading

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background02),
        verticalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(vertical = 22.dp),
        horizontalAlignment = if (isLoading) Alignment.CenterHorizontally else Alignment.Start
    ) {
        item {
            CustomSearchBar(
                onSearchClick = { searchQuery ->
                }
            )
        }

        item {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                BookRecommendationSection(
                    books = recommendedBooks,
                    onBookClick = { recommendedBook ->
                        navigator.navigateToReviewDetail(recommendedBook.id)
                    }
                )
            }
        }

        item {
            println("DEBUG: recentReviewList = $recentReviewList")
            recentReviewList?.let { reviewList ->
                RecentReviewSection(
                    review = reviewList,
                    onReviewClick = { reviewId: Long ->
                        navigator.navigateToReviewDetail(reviewList.bookId)
                    },
                    onMoreClick = {
                        navigator.navigateToRecentReview()
                    }
                )
            } ?: run {
                println("DEBUG: recentReviewList is null")
            }
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
            recentEmotionShare?.let { quote ->
                EmotionShareSection(
                    quote = quote,
                    onEmotionClick = { quoteId ->
                        navigator.navigateToEmotionShare()
                    },
                    onMoreClick = {
                        navigator.navigateToEmotionShare()
                    }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

