package com.solux.dorandoran.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostDelQuoteLikeDto(
    @SerialName("success") val success: Boolean,
    @SerialName("message") val message: String,
    @SerialName("likeCount") val likeCount: Int
)