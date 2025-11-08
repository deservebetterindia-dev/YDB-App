package com.deservebetter.ydbandroid.data.firestore

import android.util.Log
import com.deservebetter.ydbandroid.data.ProductsRepository
import com.deservebetter.ydbandroid.data.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProductsRepository {

    override fun getProducts(): Flow<List<Product>> {
        return firestore.collection("products")
            .snapshots()
            .map { querySnapshot ->
                Log.d("FirestoreSync", "Product list updated successfully.")
                querySnapshot.toObjects(Product::class.java)
            }
    }

    override suspend fun addProduct(product: Product) {
        firestore.collection("products").add(product).await()
        Log.d("FirestoreSync", "New product added successfully.")
    }
}
