package com.deservebetter.ydbandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deservebetter.ydbandroid.ui.admin.*

@Composable
fun AdminNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AdminRoute.OVERVIEW,
        modifier = modifier
    ) {
        composable(AdminRoute.OVERVIEW) { OverviewScreen() }
        composable(AdminRoute.PRODUCTS) { AdminProductsScreen() }
        composable(AdminRoute.ADD_PRODUCT) { AddProductScreen() }
        composable(AdminRoute.BLOG_POSTS) { BlogPostsScreen(navController) }
        composable(AdminRoute.CREATE_BLOG_POST) { CreateBlogPostScreen() }
        composable(AdminRoute.USER_MANAGEMENT) { UserManagementScreen() }
        composable(AdminRoute.RESEARCH_PAPERS) { ResearchPapersScreen() }
        composable(AdminRoute.SCIENCE_CONTENT) { ScienceContentScreen() }
        composable(AdminRoute.NEWSLETTER) { NewsletterScreen() }
        composable(AdminRoute.MODERATION) { ModerationScreen() }
        composable(AdminRoute.ANALYTICS) { AnalyticsScreen() }
    }
}