package com.deservebetter.ydbandroid.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.deservebetter.ydbandroid.navigation.AdminNavGraph
import com.deservebetter.ydbandroid.navigation.AdminRoute
import kotlinx.coroutines.launch

data class AdminNavItem(val route: String, val label: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPortalScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItems = listOf(
        AdminNavItem(AdminRoute.OVERVIEW, "Overview", Icons.AutoMirrored.Filled.List),
        AdminNavItem(AdminRoute.PRODUCTS, "Products", Icons.Default.Settings),
        AdminNavItem(AdminRoute.ADD_PRODUCT, "Add Product", Icons.Default.Add),
        AdminNavItem(AdminRoute.USER_MANAGEMENT, "User Management", Icons.Default.Person),
        AdminNavItem(AdminRoute.BLOG_POSTS, "Blog Posts", Icons.Default.Info)
        // Add other items here
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(16.dp)) {
                    menuItems.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(item.label) },
                            selected = navController.currentDestination?.route == item.route,
                            onClick = {
                                navController.navigate(item.route)
                                scope.launch { drawerState.close() }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Admin Portal") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) {
            AdminNavGraph(navController = navController, modifier = Modifier.padding(it))
        }
    }
}
