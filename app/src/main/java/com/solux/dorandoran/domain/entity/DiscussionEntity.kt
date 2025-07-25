package com.solux.dorandoran.domain.entity

data class DiscussionEntity(
    val id: Long,
    val title: String,
    val bookTitle: String,
    val author: String,
    val participantCount: Int,
    val imageUrl: String,
    val userProfileImage: String
)