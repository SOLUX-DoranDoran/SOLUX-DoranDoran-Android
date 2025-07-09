package com.solux.dorandoran.domain.entity

data class EmotionShareEntity(
    val id: Long,
    val bookTitle: String,
    val content: String,
    val userName: String,
    val userProfileImage: String
)

// id != userName?? 그럴리가
// 안 쓰는 요소도 entity 에 넣어야 하는 건가?