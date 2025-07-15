package com.solux.dorandoran.domain.entity

data class EmotionShareEntity(
    val id: Long,
    val bookTitle: String,
    val content: String,
    val userName: String,
    val userProfileImage: String
)