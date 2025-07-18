package com.solux.dorandoran.presentation.discuss

import androidx.lifecycle.ViewModel
import com.solux.dorandoran.domain.entity.DiscussionPageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

@HiltViewModel
class DiscussViewModel @Inject constructor() : ViewModel() {

    val sampleDiscussions = listOf(
        DiscussionPageEntity(
            id = 1,
            name = "김눈송",
            bookTitle = "로미오와 줄리엣",
            discussionTopic = "둘은 찐 사랑이 맞는가",
            bookImageUrl = "",
            authorName = "셰익스피어",
            publisher = "민음사",
            publishDate = "2008년 2월 28일",
            discussionArgument = "저게 사랑이 아니면 뭐란 말임"
        ),
        DiscussionPageEntity(
            id = 2,
            name = "이눈송",
            bookTitle = "해리포터",
            discussionTopic = "덤블도어는 옳았는가",
            bookImageUrl = "",
            authorName = "J.K. 롤링",
            publisher = "민음사",
            publishDate = "2008년 2월 28일",
            discussionArgument = "사실 전 해리포터를 안읽었어요"
        ),
        DiscussionPageEntity(
            id = 3,
            name = "박눈송",
            bookTitle = "1984",
            discussionTopic = "토론 내용 ~~",
            bookImageUrl = "",
            authorName = "조지 오웰",
            publisher = "민음사",
            publishDate = "2008년 2월 28일",
            discussionArgument = "의견 ~~~"
        ),
        DiscussionPageEntity(
            id = 4,
            name = "최눈송",
            bookTitle = "오만과 편견",
            discussionTopic = "토론 내용 ~~~",
            bookImageUrl = "",
            authorName = "제인 오스틴",
            publisher = "민음사",
            publishDate = "2008년 2월 28일",
            discussionArgument = "의견 ~~~"
        ),
        DiscussionPageEntity(
            id = 5,
            name = "눈송이",
            bookTitle = "채식주의자",
            discussionTopic = "토론 내용 ~~~",
            bookImageUrl = "",
            authorName = "한강",
            publisher = "민음사",
            publishDate = "2008년 2월 28일",
            discussionArgument = "의견 ~~~"
        )
    )


    private val _selectedDiscussion = mutableStateOf<DiscussionPageEntity?>(null)
    val selectedDiscussion: State<DiscussionPageEntity?> = _selectedDiscussion

    fun selectDiscussion(discussion: DiscussionPageEntity) {
        _selectedDiscussion.value = discussion
    }

    fun getDiscussionById(discussionId:Int):DiscussionPageEntity? {
        return sampleDiscussions.find {it.id == discussionId}
    }

    fun getDiscussionsForBook(bookTitle:String): List<DiscussionPageEntity>{
        return sampleDiscussions.filter {it.bookTitle == bookTitle}
    }

    fun getDiscussionsForSelectedBook(): List<DiscussionPageEntity> {
        return sampleDiscussions.filter { it.bookTitle == selectedDiscussion.value?.bookTitle }
    }



}