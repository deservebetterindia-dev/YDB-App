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
class ProductViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun loadProduct(productId: String) {
        viewModelScope.launch {
            val product = repository.getHomeData().products.find { it.id == productId }
            _uiState.update { it.copy(product = product) }
        }
    }

    fun onSubscribeToggled(isSubscribed: Boolean) {
        _uiState.update { it.copy(isSubscribed = isSubscribed) }
        // In a real app, this would trigger a network request
    }

    fun onAddToCartClicked() {
        _uiState.update { it.copy(cartItemCount = it.cartItemCount + 1) }
        // This would typically update a shared cart state
    }
}

data class ProductUiState(
    val product: Product? = null,
    val isSubscribed: Boolean = false,
    val cartItemCount: Int = 0
)
