package com.deservebetter.ydbandroid.ui.admin

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.deservebetter.ydbandroid.ui.theme.YDBAndroidTheme

// --- Notes for Integration ---
// ViewModel Integration:
// - Replace `mockStats` and `mockPosts` with flows from a ViewModel.
//   Example: `val stats by viewModel.summaryStats.collectAsState()`
//   Example: `val posts by viewModel.filteredPosts.collectAsState()`
//
// Navigation Integration:
// - Pass a NavController to the AdminOverviewScreen.
// - `onAddNewClick`: Use `navController.navigate("create_post_route")`
// - `onEditClick`: Use `navController.navigate("edit_post_route/{postId}")`
// - `onFilterClick`: Can show a dialog or bottom sheet for filtering options.

@Composable
fun AdminOverviewScreen(navController: NavController) {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    val windowSize = calculateWindowSizeClass(LocalContext.current as Activity)

    val isTablet = windowSize.widthSizeClass > androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Compact
    val contentPadding = if (isTablet) 20.dp else 12.dp
    val contentMaxWidth = if (isTablet) 0.7f else 0.92f

    // Mock data - replace with ViewModel state
    val mockStats = listOf(
        StatInfo("Products", "240"),
        StatInfo("Posts", "120"),
        StatInfo("Users", "1,2K")
    )
    val mockPosts = remember {
        listOf(
            PostInfo("p1", "Understanding PCOS", "Dr. Jane Doe", "Women's Health", "Published"),
            PostInfo("p2", "Ayurvedic Remedies for Insomnia", "Dr. Sanjay Gupta", "Wellness", "Draft")
        )
    }

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(modifier = Modifier.fillMaxWidth(contentMaxWidth)) {
                    Header()
                    Spacer(modifier = Modifier.height(20.dp))
                    StatsRow(stats = mockStats)
                    Spacer(modifier = Modifier.height(24.dp))
                    FilterBar(onAddNewClick = {}, onFilterClick = {})
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            items(mockPosts) { post ->
                Box(modifier = Modifier.fillMaxWidth(contentMaxWidth)) {
                    PostItem(post = post, isCompact = !isTablet, onEditClick = {}, onDeleteClick = {})
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

/** Data classes for mock UI data */
data class StatInfo(val label: String, val value: String)
data class PostInfo(val id: String, val title: String, val author: String, val category: String, val status: String)

/** Composable for the screen header */
@Composable
private fun Header() {
    Text(
        text = "Blog Posts",
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold
    )
}

/** Composable for the row of summary statistic cards. */
@Composable
fun StatsRow(stats: List<StatInfo>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stats.forEach {
            StatCard(stat = it, modifier = Modifier.weight(1f))
        }
    }
}

/** A single, reusable card for displaying a statistic. */
@Composable
fun StatCard(stat: StatInfo, modifier: Modifier = Modifier) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stat.label, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stat.value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}

/** Composable for the search and filter action bar. */
@Composable
fun FilterBar(onAddNewClick: () -> Unit, onFilterClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search posts...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier.weight(1f),
            singleLine = true
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = onFilterClick) {
            Icon(Icons.Default.FilterList, contentDescription = "Filter Posts")
        }
        Button(onClick = onAddNewClick) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text("New")
        }
    }
}

/** A single list item representing a blog post. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostItem(post: PostInfo, isCompact: Boolean, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    var menuExpanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = post.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(
                    text = "by ${post.author}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Simple custom 'chip' using Surface for compatibility
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        tonalElevation = 2.dp,
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        Text(
                            text = post.category,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Pill(text = post.status)
                }
            }

            if (isCompact) {
                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More actions for ${post.title}")
                    }
                    DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                        DropdownMenuItem(text = { Text("Edit") }, onClick = onEditClick)
                        DropdownMenuItem(text = { Text("Delete") }, onClick = onDeleteClick)
                    }
                }
            } else {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit ${post.title}")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete ${post.title}")
                }
            }
        }
    }
}

/** A simple composable for a status pill. */
@Composable
fun Pill(text: String, color: Color = MaterialTheme.colorScheme.secondaryContainer) {
    Box(
        modifier = Modifier
            .background(color, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
@Preview(showBackground = true)
fun AdminOverviewScreenPhonePreview() {
    YDBAndroidTheme {
        AdminOverviewScreen(rememberNavController())
    }
}

@Composable
@Preview(showBackground = true, device = Devices.TABLET)
fun AdminOverviewScreenTabletPreview() {
    YDBAndroidTheme {
        AdminOverviewScreen(rememberNavController())
    }
}
