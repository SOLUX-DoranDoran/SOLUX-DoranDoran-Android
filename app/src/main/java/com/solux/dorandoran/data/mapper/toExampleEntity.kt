package com.solux.dorandoran.data.mapper

import com.solux.dorandoran.data.dto.response.ResponseGetExampleDto
import com.solux.dorandoran.domain.entity.ExampleEntity

fun ResponseGetExampleDto.toExampleEntity() = ExampleEntity(
    email, firstName, avatar
)