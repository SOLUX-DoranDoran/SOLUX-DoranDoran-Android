package com.solux.dorandoran.domain.entity

data class DiscussionEntity(
    val id: Long,
    val title: String,
    val bookTitle: String,
    val author: String,
    val participantCount: Int,
    val imageUrl: String
)

// partipantCount 삭세 필요
// id와 author이 뭐가 다른지, 같다면 삭제
// 프로필 이미지 url 필요
// author은 그냥 안 쓰이네