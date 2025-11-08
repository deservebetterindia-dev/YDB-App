package com.deservebetter.ydbandroid.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class Post(
    @DocumentId
    val postId: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val content: String = "",
    val mediaURL: String? = null,
    @ServerTimestamp
    val timestamp: Timestamp? = null,
    val likeCount: Long = 0, // Use Long for Firestore numbers
    val commentCount: Long = 0,
    val tags: List<String> = emptyList()
)
