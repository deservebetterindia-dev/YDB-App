package com.deservebetter.ydbandroid.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deservebetter.ydbandroid.data.model.AdminBlogPost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class AdminBlogViewModel @Inject constructor() : ViewModel() {

    private val _blogPosts = MutableStateFlow<List<AdminBlogPost>>(emptyList())
    val blogPosts: StateFlow<List<AdminBlogPost>> = _blogPosts.asStateFlow()

    fun loadBlogPosts(context: Context) {
        viewModelScope.launch {
            val jsonString = withContext(Dispatchers.IO) {
                context.assets.open("admin_blog_posts.json").bufferedReader().use { it.readText() }
            }
            _blogPosts.value = Json.decodeFromString(jsonString)
        }
    }
}
