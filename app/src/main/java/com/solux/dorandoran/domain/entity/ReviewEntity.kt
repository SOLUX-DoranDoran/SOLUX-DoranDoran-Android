package com.solux.dorandoran.domain.entity

data class ReviewEntity(
    val id: Long,
    val bookTitle: String,
    val userName: String,
    val userProfileImage: String,
    val rating: Float,
    val content: String,
    val createdAt: String
)