package com.deservebetter.ydbandroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    val greeting: String,
    val home_cards: List<HomeCard>
)

@Serializable
data class HomeCard(
    val image_url: String,
    val title: String
)