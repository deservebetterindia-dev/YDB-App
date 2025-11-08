package com.deservebetter.ydbandroid.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deservebetter.ydbandroid.data.model.AdminBlogPost
import com.deservebetter.ydbandroid.navigation.AdminRoute
import com.deservebetter.ydbandroid.ui.viewmodel.AdminBlogViewModel

@Composable
fun BlogPostsScreen(navController: NavController, viewModel: AdminBlogViewModel = hiltViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.loadBlogPosts(context)
    }

    val blogPosts by viewModel.blogPosts.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Blog Posts", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Text("Create, edit, and manage blog posts for your website.", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Manage Blog Posts", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navController.navigate(AdminRoute.CREATE_BLOG_POST) }) {
                Icon(Icons.Default.Add, contentDescription = "New Blog Post")
                Spacer(modifier = Modifier.width(8.dp))
                Text("New Blog Post")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        BlogPostList(blogPosts)
    }
}

@Composable
private fun BlogPostList(posts: List<AdminBlogPost>) {
    Column {
        // Header
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("TITLE", modifier = Modifier.weight(0.3f), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Text("AUTHOR", modifier = Modifier.weight(0.2f), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Text("CATEGORY", modifier = Modifier.weight(0.25f), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Text("STATUS", modifier = Modifier.weight(0.15f), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            Text("ACTIONS", modifier = Modifier.weight(0.1f), style = MaterialTheme.typography.labelSmall, color = Color.Gray, textAlign = androidx.compose.ui.text.style.TextAlign.End)
        }
        Divider()
        // List
        if (posts.isEmpty()) {
            Text("No blog posts found.", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn {
                items(posts) { post ->
                    BlogPostListItem(post)
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun BlogPostListItem(post: AdminBlogPost) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(0.3f)) {
            Text(post.title, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)
            Text(post.subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Text(post.author, modifier = Modifier.weight(0.2f), style = MaterialTheme.typography.bodyMedium)
        Text(post.category, modifier = Modifier.weight(0.25f), style = MaterialTheme.typography.bodyMedium)
        Text(post.status, modifier = Modifier.weight(0.15f), style = MaterialTheme.typography.bodyMedium)
        Row(modifier = Modifier.weight(0.1f), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { /* TODO: Handle Edit */ }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
    }
}
