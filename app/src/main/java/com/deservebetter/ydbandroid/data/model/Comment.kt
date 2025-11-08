package com.deservebetter.ydbandroid.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class Comment(
    @DocumentId
    val commentId: String = "",
    val postId: String = "",
    val parentCommentId: String? = null, // Nullable for top-level comments
    val authorId: String = "",
    val authorName: String = "",
    val content: String = "",
    @ServerTimestamp
    val timestamp: Timestamp? = null,
    val likeCount: Long = 0
)
