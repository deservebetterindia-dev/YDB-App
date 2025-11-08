package com.deservebetter.ydbandroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeData(
    val greeting: String,
    val home_cards: List<HomeCard>,
    val products: List<Product>
)

@Serializable
data class HomeCard(
    val image_url: String,
    val title: String,
    val action_id: String
)

@Serializable
data class Product(
    val id: String,
    val name: String,
    val image_url: String,
    val long_description: String,
    val ingredients: List<String>,
    val price: Double,
    val category_id: String
)