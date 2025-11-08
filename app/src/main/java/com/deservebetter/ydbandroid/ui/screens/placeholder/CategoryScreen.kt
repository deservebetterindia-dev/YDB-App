package com.deservebetter.ydbandroid.ui.screens.placeholder

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CategoryScreen(id: String?) {
    Log.d("Navigation", "Navigated to CategoryScreen with id: $id")
    Text(text = "Category Screen: $id")
}