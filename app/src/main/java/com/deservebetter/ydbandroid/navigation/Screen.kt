package com.deservebetter.ydbandroid.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Shop : Screen("shop")
    object Consult : Screen("consult")
    object Forum : Screen("forum")
    object Profile : Screen("profile")
}