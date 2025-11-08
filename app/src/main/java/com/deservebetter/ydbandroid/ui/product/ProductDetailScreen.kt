package com.deservebetter.ydbandroid.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.deservebetter.ydbandroid.ui.viewmodel.ProductViewModel

@Composable
fun ProductDetailScreen(productId: String?, viewModel: ProductViewModel = hiltViewModel()) {
    LaunchedEffect(productId) {
        productId?.let { viewModel.loadProduct(it) }
    }

    val uiState by viewModel.uiState.collectAsState()
    val product = uiState.product

    if (product == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val context = LocalContext.current
        val model = if (product.image_url.startsWith("drawable/")) {
            val imageName = product.image_url.substringAfter('/').substringBefore('.')
            context.resources.getIdentifier(imageName, "drawable", context.packageName)
        } else {
            product.image_url
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            AsyncImage(
                model = model,
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth().height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = product.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.long_description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ingredients", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            product.ingredients.forEach { ingredient ->
                Text(text = "• $ingredient", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "₹${product.price}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Subscribe & save")
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = uiState.isSubscribed,
                    onCheckedChange = { viewModel.onSubscribeToggled(it) }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.onAddToCartClicked() }, modifier = Modifier.fillMaxWidth()) {
                Text("Add to Cart (${uiState.cartItemCount})")
            }
        }
    }
}
