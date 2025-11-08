package com.deservebetter.ydbandroid.di

import android.content.Context
import com.deservebetter.ydbandroid.data.HomeRepository
import com.deservebetter.ydbandroid.data.ProductsRepository
import com.deservebetter.ydbandroid.data.firestore.ProductsRepositoryImpl
import com.deservebetter.ydbandroid.data.local.HomeRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideHomeRepository(
        assetManager: android.content.res.AssetManager,
        ioDispatcher: CoroutineDispatcher
    ): HomeRepository {
        return HomeRepositoryImpl(assetManager, ioDispatcher)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(firestore: FirebaseFirestore): ProductsRepository {
        return ProductsRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideAssetManager(@ApplicationContext context: Context): android.content.res.AssetManager {
        return context.assets
    }
}
