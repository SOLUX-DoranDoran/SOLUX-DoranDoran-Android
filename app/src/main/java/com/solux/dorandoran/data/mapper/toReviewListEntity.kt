package com.solux.dorandoran.data.mapper

import com.solux.dorandoran.data.dto.response.ResponseGetBookReviewItemDto
import com.solux.dorandoran.data.dto.response.ResponseGetRecentReviewDto
import com.solux.dorandoran.domain.entity.ReviewListEntity


// 최근 리뷰 목록 DTO → Entity
fun ResponseGetRecentReviewDto.toReviewListEntity() = ReviewListEntity(
    id = id,
    bookTitle = bookTitle,
    coverImageUrl = coverImageUrl,
    content = content,
    rating = rating,
    createdAt = createdAt,
    nickname = nickname,
    profileImage = profileImage
)

// 도서별 리뷰 아이템 DTO → Entity
fun ResponseGetBookReviewItemDto.toReviewListEntity() = ReviewListEntity(
    id = id,
    bookTitle = bookTitle,
    coverImageUrl = coverImageUrl,
    content = content,
    rating = rating,
    createdAt = createdAt,
    nickname = nickname,
    profileImage = profileImage
)