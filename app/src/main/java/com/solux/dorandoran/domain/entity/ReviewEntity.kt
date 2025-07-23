package com.solux.dorandoran.domain.entity

data class ReviewEntity(
    // 백엔드 필드명에 맞게 수정
    val id: Long,
    val bookTitle: String,
    val coverImageUrl : String,
    val content: String,
    val rating: Int,
    val createdAt: String,
    val nickname: String,
    val profileImage: String
)