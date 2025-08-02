package com.solux.dorandoran.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponsePostQuoteDto(
    @SerialName("message") val message: String,
    @SerialName("quoteId") val quoteId: Long
)