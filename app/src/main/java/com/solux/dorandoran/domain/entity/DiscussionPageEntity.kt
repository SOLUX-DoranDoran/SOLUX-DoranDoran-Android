package com.solux.dorandoran.domain.entity

data class Comment(
    val id: Int,
    val name: String,
    val content: String,
    val timestamp: String
)

data class DiscussionArgument(
    val id: Int,
    val name: String,
    val content: String,
    val timestamp: String,
    val comments: List<Comment> = emptyList()
)

data class DiscussionPageEntity (

    val id: Int,
    val name: String,
    val bookTitle: String,
    val discussionTopic: String,
    val bookImageUrl: String,
    val authorName: String,
    val publisher: String,
    val publishDate: String,
    val arguments: List<DiscussionArgument> = emptyList()

)

