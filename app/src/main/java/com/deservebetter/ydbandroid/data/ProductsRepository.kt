package com.deservebetter.ydbandroid.data

import com.deservebetter.ydbandroid.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<List<Product>>
    suspend fun addProduct(product: Product)
}
