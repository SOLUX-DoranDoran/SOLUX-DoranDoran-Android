package com.solux.dorandoran.presentation.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solux.dorandoran.domain.entity.DiscussionEntity
import com.solux.dorandoran.domain.entity.EmotionShareEntity
import com.solux.dorandoran.domain.entity.RecommendedBookEntity
import com.solux.dorandoran.domain.entity.ReviewEntity
import com.solux.dorandoran.domain.repository.RecommendedBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recommendedBookRepository: RecommendedBookRepository
) : ViewModel() {

    private val _recommendedBooks = mutableStateOf<List<RecommendedBookEntity>>(emptyList())
    val recommendedBooks: State<List<RecommendedBookEntity>> = _recommendedBooks

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        getRecommendedBooks()
    }

    private fun getRecommendedBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                recommendedBookRepository.getRecommendedBooks()
                    .onSuccess { books ->
                        _recommendedBooks.value = books
                    }
                    .onFailure { error ->
                        _errorMessage.value = error.message
                        _recommendedBooks.value = getDummyRecommendedBooks()
                    }
            } catch (e: Exception) {
                _recommendedBooks.value = getDummyRecommendedBooks()
            }

            _isLoading.value = false
        }
    }

    private fun getDummyRecommendedBooks() = listOf(
        RecommendedBookEntity(
            id = 1,
            title = "skaldi wkqghkwja의 기적",
            author = "히가시노 게이고",
            publisher = "현대문학",
            publisherDate = "2012-12-19",
            coverImageUrl = "https://raw.githubusercontent.com/dhhe1234/solux/main/나미야잡화점의기적_표지.jpg"
        ),
        RecommendedBookEntity(
            id = 2,
            title = "미드나잇 라이브러리",
            author = "매트 헤이그",
            publisher = "인플루엔셜",
            publisherDate = "2021-04-28",
            coverImageUrl = "https://raw.githubusercontent.com/dhhe1234/solux/main/미드나잇라이브러리_표지.jpg"
        ),
        RecommendedBookEntity(
            id = 3,
            title = "파친코",
            author = "이민진",
            publisher = "문학사상사",
            publisherDate = "2018-03-19",
            coverImageUrl = "https://raw.githubusercontent.com/dhhe1234/solux/main/파친코_표지.jpg"
        )
    )

    val recentReview = ReviewEntity(
        id = 1,
        bookTitle = "소년이 온다",
        coverImageUrl = "",
        content = "한강 작가님 최고",
        rating = 3,
        createdAt = "방금 전",
        nickname = "송이",
        profileImage = "",
        bookId = 134414144124214124,
        userId = 342432412414214,
        likeCount = 5,
        commentCount = 1,
        isLiked = true
    )

    val hotDiscussions = DiscussionEntity(
        id = 1,
        title = "돌은 찐 사랑이 맞는가",
        bookTitle = "로미오와 줄리엣",
        author = "셰익스피어",
        participantCount = 17,
        imageUrl = "",
        userProfileImage = ""
    )

    val emotionShares = EmotionShareEntity(
        id = 1,
        bookTitle = "로미오와 줄리엣",
        content = "이별은 아직엄 달콤한 슬픔이기에 내일이 될 때까지 안녕을 말하네",
        userName = "송이",
        userProfileImage = "",
        likeCount = 0,
        isLiked = false,
        createdAt = ""
    )
}