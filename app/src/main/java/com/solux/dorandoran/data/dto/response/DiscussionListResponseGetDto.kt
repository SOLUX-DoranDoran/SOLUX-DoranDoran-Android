package com.solux.dorandoran.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscussionListResponseGetDto (
    @SerialName("id") val id: Int,
    @SerialName("bookId") val bookId: Int,
    @SerialName("memberId") val memberId: Int,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("createdAt") val createdAt: String
)