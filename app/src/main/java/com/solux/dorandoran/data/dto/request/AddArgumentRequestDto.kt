package com.solux.dorandoran.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddArgumentRequestDto (
    @SerialName("content") val content: String
)