package com.solux.dorandoran.data.mapper

import com.solux.dorandoran.data.dto.response.BookInfoResponseGetDto
import com.solux.dorandoran.domain.entity.BookInfoEntity

fun BookInfoResponseGetDto.toBookInfoEntity() = BookInfoEntity(
    id, title, author, publisher, publisherDate, coverImageUrl
)