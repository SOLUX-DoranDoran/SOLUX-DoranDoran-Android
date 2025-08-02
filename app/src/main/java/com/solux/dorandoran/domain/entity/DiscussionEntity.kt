package com.solux.dorandoran.domain.entity

data class DiscussionEntity(
    val boardId: Long,
    val bookId: Long,
    val memberId: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val bookTitle: String,
    val author: String,
    val imageUrl: String,
    val userProfileImage: String
)