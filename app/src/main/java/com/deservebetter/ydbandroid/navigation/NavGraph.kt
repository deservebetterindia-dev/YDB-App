package com.deservebetter.ydbandroid.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.deservebetter.ydbandroid.ui.admin.AdminPortalScreen
import com.deservebetter.ydbandroid.ui.category.CategoryScreen
import com.deservebetter.ydbandroid.ui.product.ProductDetailScreen
import com.deservebetter.ydbandroid.ui.screens.HomeScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.CartScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ConsultScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ForumScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ProfileScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ShopScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Route.HOME,
        modifier = modifier
    ) {
        composable(Route.HOME) { HomeScreen(navController) }
        composable(
            route = Route.PRODUCT_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            ProductDetailScreen(productId = backStackEntry.arguments?.getString("id"))
        }
        composable(
            route = Route.CATEGORY,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            CategoryScreen(categoryId = backStackEntry.arguments?.getString("id"), navController = navController)
        }
        composable(Route.CART) { CartScreen() }
        composable(Route.SHOP) { ShopScreen() }
        composable(Route.CONSULT) { ConsultScreen() }
        composable(Route.FORUM) { ForumScreen() }
        composable(Route.PROFILE) { ProfileScreen(navController) }
        composable(Route.ADMIN_PORTAL) { AdminPortalScreen() }
    }
}