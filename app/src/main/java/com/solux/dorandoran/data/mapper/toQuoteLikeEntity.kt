package com.solux.dorandoran.data.mapper

import com.solux.dorandoran.data.dto.ResponsePostDelQuoteLikeDto
import com.solux.dorandoran.domain.entity.QuoteLikeEntity

fun ResponsePostDelQuoteLikeDto.toQuoteLikeEntity() = QuoteLikeEntity(
    success = success,
    message = message,
    likeCount = likeCount
)