package com.solux.dorandoran.presentation.discuss

import androidx.lifecycle.ViewModel
import com.solux.dorandoran.domain.entity.DiscussionPageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscussViewModel @Inject constructor() : ViewModel() {

    val sampleDiscussions = listOf(
        DiscussionPageEntity(
            id = 1,
            name = "김눈송",
            bookTitle = "로미오와 줄리엣",
            discussionTopic = "둘은 찐 사랑이 맞는가",
            bookImageUrl = "",
            authorName = ""
        ),
        DiscussionPageEntity(
            id = 2,
            name = "이눈송",
            bookTitle = "해리포터",
            discussionTopic = "덤블도어는 옳았는가",
            bookImageUrl = "",
            authorName = ""
        ),
        DiscussionPageEntity(
            id = 3,
            name = "박눈송",
            bookTitle = "1984",
            discussionTopic = "토론 내용 ~~",
            bookImageUrl = "",
            authorName = ""
        ),
        DiscussionPageEntity(
            id = 4,
            name = "최눈송",
            bookTitle = "오만과 편견",
            discussionTopic = "토론 내용 ~~~",
            bookImageUrl = "",
            authorName = ""
        ),
        DiscussionPageEntity(
            id = 5,
            name = "눈송이",
            bookTitle = "채식주의자",
            discussionTopic = "토론 내용 ~~~",
            bookImageUrl = "",
            authorName = ""
        )
    )
}