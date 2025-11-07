package com.deservebetter.ydbandroid.data.local

import android.content.res.AssetManager
import com.deservebetter.ydbandroid.data.HomeRepository
import com.deservebetter.ydbandroid.data.model.HomeData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val assets: AssetManager,
    private val ioDispatcher: CoroutineDispatcher
) : HomeRepository {

    override suspend fun getHomeData(): HomeData {
        return withContext(ioDispatcher) {
            val jsonString = assets.open("mock_home.json").bufferedReader().use { it.readText() }
            Json.decodeFromString<HomeData>(jsonString)
        }
    }
}