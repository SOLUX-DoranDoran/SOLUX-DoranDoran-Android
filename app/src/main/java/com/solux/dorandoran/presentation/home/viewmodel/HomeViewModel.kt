package com.solux.dorandoran.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.dorandoran.domain.entity.DiscussionEntity
import com.solux.dorandoran.domain.entity.EmotionShareEntity
import com.solux.dorandoran.domain.entity.QuoteEntity
import com.solux.dorandoran.domain.entity.RecommendedBookEntity
import com.solux.dorandoran.domain.entity.ReviewListEntity
import com.solux.dorandoran.domain.repository.QuoteRepository
import com.solux.dorandoran.domain.repository.RecommendedBookRepository
import com.solux.dorandoran.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendedBookRepository: RecommendedBookRepository,
    private val reviewRepository: ReviewRepository,
    private val quoteRepository: QuoteRepository
) : ViewModel() {

    private val _recommendedBooks = mutableStateOf<List<RecommendedBookEntity>>(emptyList())
    val recommendedBooks: State<List<RecommendedBookEntity>> = _recommendedBooks

    private val _recentReviewList = mutableStateOf<ReviewListEntity?>(null)
    val recentReviewList: State<ReviewListEntity?> = _recentReviewList

    private val _recentEmotionShare = mutableStateOf<QuoteEntity?>(null)
    val recentEmotionShare: State<QuoteEntity?> = _recentEmotionShare

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        getRecommendedBooks()
        getRecentReview()
        getRecentEmotionShare()
    }

    private fun getRecommendedBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            recommendedBookRepository.getRecommendedBooks()
                .onSuccess { books ->
                    _recommendedBooks.value = books
                }
                .onFailure { error ->
                    _errorMessage.value = error.message
                }

            _isLoading.value = false
        }
    }

    private fun getRecentReview() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = reviewRepository.getRecentReviews(
                    sort = "recent",
                    page = 1,
                    size = 1
                )

                result
                    .onSuccess { reviewList ->
                        _recentReviewList.value = reviewList.firstOrNull()

                    }
                    .onFailure { error ->
                        _errorMessage.value = "최근 리뷰 로딩 실패: ${error.message}"
                        _recentReviewList.value = null
                    }

            } catch (e: Exception) {
                _errorMessage.value = "최근 리뷰 로딩 중 알 수 없는 에러 발생: ${e.message}"
                _recentReviewList.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    // TODO: 최근 토론 가져오기


    private fun getRecentEmotionShare() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val result = quoteRepository.getRecentQuote()

                result
                    .onSuccess { quote ->
                        _recentEmotionShare.value = quote
                    }
                    .onFailure { error ->
                        _errorMessage.value = "최근 감성 글귀 로딩 실패: ${error.message}"
                        _recentEmotionShare.value = null
                    }

            } catch (e: Exception) {
                _errorMessage.value = "최근 감성 글귀 로딩 중 알 수 없는 에러 발생: ${e.message}"
                _recentEmotionShare.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    val hotDiscussions = DiscussionEntity(
        boardId = 1,
        bookId = 2,
        memberId = 13,
        title = "돌은 찐 사랑이 맞는가",
        content = "",
        createdAt = "24-12-03",
        bookTitle = "로미오와 줄리엣",
        author = "셰익스피어",
        imageUrl = "",
        userProfileImage = ""
    )
}