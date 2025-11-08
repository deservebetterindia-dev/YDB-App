package com.deservebetter.ydbandroid.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deservebetter.ydbandroid.data.HomeRepository
import com.deservebetter.ydbandroid.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    fun loadCategory(categoryId: String) {
        viewModelScope.launch {
            val allProducts = repository.getHomeData().products
            val filteredProducts = allProducts.filter { it.category_id == categoryId }
            _uiState.update { it.copy(products = filteredProducts, categoryId = categoryId) }
        }
    }
}

data class CategoryUiState(
    val categoryId: String? = null,
    val products: List<Product> = emptyList()
)
