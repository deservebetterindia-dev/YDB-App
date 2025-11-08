package com.deservebetter.ydbandroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AdminBlogPost(
    val id: String,
    val title: String,
    val subtitle: String,
    val author: String,
    val category: String,
    val status: String // e.g., "Published" or "Draft"
)
