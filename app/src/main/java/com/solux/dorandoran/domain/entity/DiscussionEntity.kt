package com.solux.dorandoran.domain.entity

data class DiscussionEntity(
    val id: Long,
    val title: String,
    val bookTitle: String,
    val author: String,
    // 홈 화면에서 사용하지 않음
    val participantCount: Int,
    val imageUrl: String,
    // 유저 프로필 이미지 추가
    val userProfileImage: String
)