package com.deservebetter.ydbandroid.navigation

object RouteUtils {
    fun buildProductDetailRoute(id: String): String {
        return Screen.ProductDetail.createRoute(id)
    }

    fun buildCategoryRoute(id: String): String {
        return Screen.Category.createRoute(id)
    }

    fun parseId(route: String?): String? {
        return route?.substringAfterLast('/')
    }
}
