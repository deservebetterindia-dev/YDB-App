package com.deservebetter.ydbandroid.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Shop,
        Screen.Consult,
        Screen.Forum,
        Screen.Profile
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(getIconForScreen(screen), contentDescription = null) },
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}

@Composable
private fun getIconForScreen(screen: Screen) = when (screen) {
    Screen.Home -> Icons.Default.Home
    Screen.Shop -> Icons.Default.ShoppingCart
    Screen.Consult -> Icons.Default.Favorite
    Screen.Forum -> Icons.Default.MailOutline
    Screen.Profile -> Icons.Default.AccountCircle
}