package com.deservebetter.ydbandroid.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AdminDashboardData(
    val admin_user: AdminUser,
    val summary_cards: List<SummaryCard>,
    val recent_activity: List<RecentActivity>
)

@Serializable
data class AdminUser(
    val name: String,
    val email: String
)

@Serializable
data class SummaryCard(
    val title: String,
    val count: Int,
    val route: String
)

@Serializable
data class RecentActivity(
    val action: String,
    val details: String,
    val timestamp: String
)
