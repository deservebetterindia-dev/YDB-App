package com.deservebetter.ydbandroid.ui.screens.placeholder

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductDetailScreen(id: String?) {
    Log.d("Navigation", "Navigated to ProductDetailScreen with id: $id")
    Text(text = "Product Detail Screen: $id")
}