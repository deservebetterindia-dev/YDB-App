package com.deservebetter.ydbandroid.ui.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.deservebetter.ydbandroid.navigation.Route
import com.deservebetter.ydbandroid.ui.components.ProductCard
import com.deservebetter.ydbandroid.ui.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(categoryId: String?, navController: NavHostController, viewModel: CategoryViewModel = hiltViewModel()) {
    LaunchedEffect(categoryId) {
        categoryId?.let { viewModel.loadCategory(it) }
    }

    val uiState by viewModel.uiState.collectAsState()

    if (uiState.products.isEmpty() && uiState.categoryId != null) {
        EmptyState(navController)
    } else if (uiState.categoryId == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(text = "Showing products for category ${uiState.categoryId}")
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(uiState.products) {
                ProductCard(product = it, onClick = { /* Navigate to product detail */ })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun EmptyState(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("No products found for this category.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(Route.SHOP) }) {
                Text("Shop All Products")
            }
        }
    }
}
