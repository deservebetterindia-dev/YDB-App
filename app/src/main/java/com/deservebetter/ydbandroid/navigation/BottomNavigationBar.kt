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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem(
    val screen: Screen,
    val icon: ImageVector
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem(Screen.Home, Icons.Default.Home),
        NavItem(Screen.Shop, Icons.Default.ShoppingCart),
        NavItem(Screen.Consult, Icons.Default.Favorite),
        NavItem(Screen.Forum, Icons.Default.MailOutline),
        NavItem(Screen.Profile, Icons.Default.AccountCircle)
    )

    NavigationBar {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = null) },
                selected = currentRoute == item.screen.route,
                onClick = { navController.navigate(item.screen.route) }
            )
        }
    }
}
