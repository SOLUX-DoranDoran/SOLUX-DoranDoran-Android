package com.solux.dorandoran.domain.entity

data class BookEntity(
    val id: Long,
    val title: String,
    val author: String,
    val imageUrl: String,
    val rating: Float,
    val category: String
)

// category 필요 없을 듯 애초에 왜 넣은 거지;;;