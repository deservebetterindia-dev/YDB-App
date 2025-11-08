package com.deservebetter.ydbandroid.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.deservebetter.ydbandroid.navigation.Screen
import com.deservebetter.ydbandroid.ui.components.GreetingHeader
import com.deservebetter.ydbandroid.ui.components.HomeCard
import com.deservebetter.ydbandroid.ui.components.SearchBar
import com.deservebetter.ydbandroid.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val homeData by viewModel.homeData.collectAsState()

    homeData?.let { data ->
        LazyColumn(modifier = modifier.padding(16.dp)) {
            item {
                GreetingHeader(data.greeting)
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                SearchBar()
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(data.home_cards) { card ->
                HomeCard(card = card) {
                    val route = if (card.action_id.startsWith("p")) {
                        Screen.ProductDetail.createRoute(card.action_id)
                    } else {
                        Screen.Category.createRoute(card.action_id)
                    }
                    navController.navigate(route)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    } ?: Text("Loading...")
}