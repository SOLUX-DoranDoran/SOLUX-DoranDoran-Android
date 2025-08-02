package com.solux.dorandoran.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddCommentRequestDto (
    @SerialName("content") val content: String,
    @SerialName("parentId") val parentId: Int?
)