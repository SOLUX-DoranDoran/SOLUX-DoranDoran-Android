package com.solux.dorandoran.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.solux.dorandoran.domain.entity.BookEntity
import com.solux.dorandoran.domain.entity.DiscussionEntity
import com.solux.dorandoran.domain.entity.EmotionShareEntity
import com.solux.dorandoran.domain.entity.ReviewEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val recommendedBooks = listOf(
        BookEntity(
            id = 1,
            title = "소년이 온다",
            author = "한강",
            imageUrl = "",
            rating = 4.8f,
            category = "소설"
        ),
        BookEntity(
            id = 2,
            title = "로미오와 줄리엣",
            author = "셰익스피어",
            imageUrl = "",
            rating = 4.5f,
            category = "희곡"
        ),
        BookEntity(
            id = 3,
            title = "소년이 온다",
            author = "한강",
            imageUrl = "",
            rating = 4.8f,
            category = "소설"
        )
    )

    val recentReview =
        ReviewEntity(
            id = 1,
            bookTitle = "소년이 온다",
            coverImageUrl = "",
            content = "한강 작가님 최고",
            rating = 3,
            createdAt = "방금 전",
            nickname = "송이",
            profileImage = "",
    )

    val hotDiscussions =
        DiscussionEntity(
            id = 1,
            title = "돌은 찐 사랑이 맞는가",
            bookTitle = "로미오와 줄리엣",
            author = "셰익스피어",
            participantCount = 17,
            imageUrl = "",
            userProfileImage=""
    )

    val emotionShares =
        EmotionShareEntity(
            id = 1,
            bookTitle = "로미오와 줄리엣",
            content = "이별은 아직엄 달콤한 슬픔이기에 내일이 될 때까지 안녕을 말하네",
            userName = "송이",
            userProfileImage = "",
            likeCount = 0,
            createdAt = ""
        )
}