package com.solux.dorandoran.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddDiscussionRequestDto (
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("bookTitle") val bookTitle: String
)