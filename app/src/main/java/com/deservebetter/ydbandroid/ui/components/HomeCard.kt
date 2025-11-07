package com.deservebetter.ydbandroid.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.deservebetter.ydbandroid.data.model.HomeCard

@Composable
fun HomeCard(card: HomeCard, onClick: () -> Unit) {
    val context = LocalContext.current
    val model = if (card.image_url.startsWith("drawable/")) {
        val imageName = card.image_url.substringAfter('/').substringBefore('.')
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    } else {
        card.image_url
    }

    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Box {
            AsyncImage(
                model = model,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            if (card.title.isNotEmpty()) {
                Text(
                    text = card.title,
                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
                )
            }
        }
    }
}