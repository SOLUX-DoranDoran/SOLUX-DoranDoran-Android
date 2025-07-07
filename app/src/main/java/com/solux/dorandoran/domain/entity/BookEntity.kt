package com.solux.dorandoran.domain.entity

data class BookEntity(
    val id: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val rating: Float,
    val category: String
)