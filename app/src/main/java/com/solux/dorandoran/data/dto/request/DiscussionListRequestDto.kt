package com.solux.dorandoran.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscussionListRequestDto (
    @SerialName("page") val page: Int,
    @SerialName("size") val size: Int
)