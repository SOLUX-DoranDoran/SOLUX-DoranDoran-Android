package com.solux.dorandoran.domain.entity

data class ReviewEntity(
    val id: Long,
    val bookTitle: String,
    val userName: String,
    val userProfileImage: String,
    // 책 표지 이미지 포함
    val imageURL : String,
    val rating: Float,
    val content: String,
    val createdAt: String
)

// 다른 페이지까지 고려를 해서 entity 를 짜야 하는 건가?