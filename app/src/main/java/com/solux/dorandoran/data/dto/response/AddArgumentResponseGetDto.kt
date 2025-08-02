package com.solux.dorandoran.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddArgumentResponseGetDto (
    @SerialName("id") val id: Int,
    @SerialName("memberNickname") val memberNickname: String,
    @SerialName("content") val content: String,
    @SerialName("createdAt") val createdAt: String,
    @SerialName("parentId") val parentId: Int?
)