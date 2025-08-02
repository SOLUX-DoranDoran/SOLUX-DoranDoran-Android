package com.solux.dorandoran.domain.entity

data class DiscussPageEntity (
    val id: Int,
    val bookId: Int,
    val memberId: Int,
    val bookTitle: String,
    val content: String,
    val createdAt: String
)