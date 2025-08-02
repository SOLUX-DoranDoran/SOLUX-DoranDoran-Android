package com.solux.dorandoran.data.mapper

import com.solux.dorandoran.data.dto.response.AddArgumentResponseGetDto
import com.solux.dorandoran.data.dto.response.AddCommentResponseGetDto
import com.solux.dorandoran.data.dto.response.ArgumentListResponseGetDto
import com.solux.dorandoran.data.dto.response.BookDiscussionListResponseGetDto
import com.solux.dorandoran.domain.entity.DiscussCommentEntity

fun AddArgumentResponseGetDto.toDiscussCommentEntity() = DiscussCommentEntity(
    id, content, createdAt, memberNickname, parentId
)

fun AddCommentResponseGetDto.toDiscussCommentEntity() = DiscussCommentEntity(
    id, content, createdAt, memberNickname, parentId
)

fun ArgumentListResponseGetDto.toDiscussCommentEntity() = DiscussCommentEntity(
    id, content, createdAt, memberNickname, parentId
)