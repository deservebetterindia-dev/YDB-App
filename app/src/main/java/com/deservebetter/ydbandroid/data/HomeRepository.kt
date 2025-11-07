package com.deservebetter.ydbandroid.data

import com.deservebetter.ydbandroid.data.model.HomeData

interface HomeRepository {
    suspend fun getHomeData(): HomeData
}