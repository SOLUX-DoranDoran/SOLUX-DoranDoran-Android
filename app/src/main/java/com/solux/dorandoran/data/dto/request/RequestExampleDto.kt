package com.solux.dorandoran.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestExampleDto (
    @SerialName("userId") val userId: Long,
    @SerialName("storeId") val storeId: Long
)