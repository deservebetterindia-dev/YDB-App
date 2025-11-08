package com.deservebetter.ydbandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deservebetter.ydbandroid.ui.screens.HomeScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ConsultScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ForumScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ProfileScreen
import com.deservebetter.ydbandroid.ui.screens.placeholder.ShopScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Shop.route) { ShopScreen() }
        composable(Screen.Consult.route) { ConsultScreen() }
        composable(Screen.Forum.route) { ForumScreen() }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }
}