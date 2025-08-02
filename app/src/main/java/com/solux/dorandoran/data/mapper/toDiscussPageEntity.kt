package com.solux.dorandoran.data.mapper

import com.solux.dorandoran.data.dto.response.AddDiscussionResponseGetDto
import com.solux.dorandoran.data.dto.response.BookDiscussionListResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussDetailResponseGetDto
import com.solux.dorandoran.data.dto.response.DiscussionListResponseGetDto
import com.solux.dorandoran.domain.entity.DiscussPageEntity

fun DiscussionListResponseGetDto.toDiscussPageEntity () = DiscussPageEntity(
    id,bookId, memberId, title, content, createdAt
)

fun BookDiscussionListResponseGetDto.toDiscussPageEntity () = DiscussPageEntity(
    boardId, memberId, bookId, title, content, createdAt
)

fun AddDiscussionResponseGetDto.toDiscussPageEntity () = DiscussPageEntity(
    boardId, memberId, bookId, title, content, createdAt
)

fun DiscussDetailResponseGetDto.toDiscussPageEntity () = DiscussPageEntity(
    boardId, memberId, bookId, title, content, createdAt
)