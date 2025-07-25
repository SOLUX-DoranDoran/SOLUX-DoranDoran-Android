package com.solux.dorandoran.presentation.mypage.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.solux.dorandoran.domain.entity.EmotionShareEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmotionShareViewModel @Inject constructor() : ViewModel() {

    private val _emotionShareList = mutableStateListOf(
        EmotionShareEntity(
            id = 1,
            bookTitle = "로미오와 줄리엣",
            content = "이별은 이처럼 달콤한 슬픔이기에 내일이 될 때까지 안녕을 말하네",
            userName = "송이",
            userProfileImage = "",
            likeCount = 5,
            isLiked = false,
            createdAt = ""
        ),
        EmotionShareEntity(
            id = 2,
            bookTitle = "소년이 온다",
            content = "어떤 기억은 아름다운 없습니다. 시간이 흘러 기억이 흐릿해지는 게 아니라, 오히려 그 기억만 남고 다른 모든 것이 사라져 마땅합니다.",
            userName = "송이",
            userProfileImage = "",
            likeCount = 19,
            isLiked = true,
            createdAt = ""
        ),
        EmotionShareEntity(
            id = 3,
            bookTitle = "소년이 온다",
            content = "나는 싸우고 있습니다. 남마다 혼자서 싸웁니다. 삶이라는 것은, 아직도 살아있는 사람들과 씨름하는 것입니다.",
            userName = "송이",
            userProfileImage = "",
            likeCount = 12,
            isLiked = false,
            createdAt = ""
        ),
        EmotionShareEntity(
            id = 4,
            bookTitle = "위대한 개츠비",
            content = "그래서 우리는 계속 노를 젓는다, 조류를 거슬러서, 끊임없이 과거로 밀려나면서.",
            userName = "민수",
            userProfileImage = "",
            likeCount = 8,
            isLiked = true,
            createdAt = ""
        ),
        EmotionShareEntity(
            id = 5,
            bookTitle = "어린 왕자",
            content = "사람은 오직 마음으로만 올바르게 볼 수 있다. 가장 중요한 것은 눈에 보이지 않는다.",
            userName = "지영",
            userProfileImage = "",
            likeCount = 0,
            isLiked = false,
            createdAt = ""
        )
    )

    val emotionShareList: List<EmotionShareEntity> = _emotionShareList

    fun toggleLike(emotionId: Long) {
        val index = _emotionShareList.indexOfFirst { it.id == emotionId }
        if (index >= 0) {
            val currentEmotion = _emotionShareList[index]
            val updatedEmotion = currentEmotion.copy(
                isLiked = !currentEmotion.isLiked,
                likeCount = if (currentEmotion.isLiked) {
                    currentEmotion.likeCount - 1
                } else {
                    currentEmotion.likeCount + 1
                }
            )
            _emotionShareList[index] = updatedEmotion
        }
    }
}